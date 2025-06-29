package lottery.service;

import jakarta.annotation.PostConstruct;
import lottery.model.MultiDrawResponse;
import lottery.model.Prize;
import lottery.model.StatsResponse;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class LotteryService {

    public static final String noPrize = "THANK_YOU";
    public static final String duplicate = "DUPLICATE_DRAW";
    private List<Prize> prizeList;
    private List<String> drawnUsers = new ArrayList<>();

    @PostConstruct
    void init() {
        prizeList = new ArrayList<>(Arrays.asList(
                new Prize("iPhone", 0.05, 10),
                new Prize("iPad", 0.1, 20),
                new Prize("Coupon", 0.15, 30)
        ));
    }

    public MultiDrawResponse multiDraw(String userId, int count) {
        if (drawnUsers.contains(userId)) {
            return MultiDrawResponse.builder().userId(userId).results(List.of(duplicate)).build();
        }
        drawnUsers.add(userId);

        List<String> results = IntStream.range(0, count)
                .mapToObj(i -> singleDraw())
                .collect(Collectors.toList());
        return MultiDrawResponse.builder().userId(userId).results(results).build();
    }

    public String singleDraw() {
        List<Prize> available = getPrizesAvailable();
        double[] cumulative = preparePrizeWheel(available);
        Prize prize = drawPrize(cumulative, available);

        if (prize.tryDecrement()) {
            return prize.getName();
        } else {
            return noPrize;
        }
    }

    private List<Prize> getPrizesAvailable() {
        List<Prize> available = prizeList.stream()
                .filter(p -> p.getRemaining() > 0)
                .collect(Collectors.toCollection(ArrayList::new));
        double thankYouProb = 1.0 - available.stream().mapToDouble(Prize::getProbability).sum();
        available.add(new Prize(noPrize, thankYouProb, Integer.MAX_VALUE));
        return available;
    }

    private static double[] preparePrizeWheel(List<Prize> available) {
        double totalProb = available.stream().mapToDouble(Prize::getProbability).sum();
        double[] cumulative = new double[available.size()];

        double running = 0;
        for (int i = 0; i < available.size(); i++) {
            double normalized = available.get(i).getProbability() / totalProb;
            running += normalized;
            cumulative[i] = running;
        }
        return cumulative;
    }

    private Prize drawPrize(double[] cumulative, List<Prize> available) {
        int index = findPrizeIndex(Math.random(), cumulative);
        return available.get(index);
    }

    private int findPrizeIndex(double random, double[] cumulative) {
        for (int i = 0; i < cumulative.length; i++) {
            if (random < cumulative[i]) {
                return i;
            }
        }
        return cumulative.length - 1;
    }

    public StatsResponse getStats() {
        Map<String, Integer> remaining = new LinkedHashMap<>();
        for (Prize prize : prizeList) {
            remaining.put(prize.getName(), prize.getRemaining());
        }
        return new StatsResponse(remaining, drawnUsers.size());
    }
}
