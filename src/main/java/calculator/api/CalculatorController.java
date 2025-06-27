package calculator.api;

import calculator.service.CalculatorService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    public ResultDto calculate(@RequestBody OperationRequest req) {
        return calculatorService.calculate(req.getLeft(), req.getRight(), req.getOperation());
    }

    @PostMapping("/undo")
    public ResultDto undo() {
        return calculatorService.undo();
    }

    @PostMapping("/redo")
    public ResultDto redo() {
        return calculatorService.redo();
    }

    @GetMapping("/current")
    public BigDecimal current() { return calculatorService.current(); }
}

