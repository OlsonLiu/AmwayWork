package lottery.api;

import lottery.model.DrawRequest;
import lottery.model.DrawResponse;
import lottery.model.MultiDrawResponse;
import lottery.model.StatsResponse;
import lottery.service.LotteryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lottery")
public class LotteryController {

    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @PostMapping("/draw")
    public MultiDrawResponse draw(@RequestBody DrawRequest request) {
        return lotteryService.multiDraw(request.getUserId(), request.getCount());
    }

    @GetMapping("/stats")
    public StatsResponse stats() {
        return lotteryService.getStats();
    }
}