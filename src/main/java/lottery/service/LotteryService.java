package lottery.service;

import lottery.model.DrawResponse;
import lottery.model.StatsResponse;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class LotteryService {

    public DrawResponse draw(String userId) {
        return new DrawResponse(userId, "iPhone");
    }

    public StatsResponse getStats() {
        Map<String, Integer> remaining = new LinkedHashMap<>();
        remaining.put("iPhone", 10);
        remaining.put("iPad", 20);
        remaining.put("Coupon", 30);
        return new StatsResponse(remaining, 1234);
    }
}
