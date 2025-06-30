package calculator.service;

import calculator.model.ResultDto;
import calculator.model.OperationCommand;
import calculator.model.OperationType;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CalculatorService {
    private int cursor;
    private BigDecimal lastResult;
    private List<OperationCommand> history;

    @PostConstruct
    void init() {
        history = new ArrayList<>();
        cursor = 0;
        lastResult = BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP);
    }

    public ResultDto calculate(BigDecimal left, BigDecimal right, OperationType operationType) {
        OperationCommand cmd = new OperationCommand(left, right, operationType);
        if (cursor < history.size()) {
            history.subList(cursor, history.size()).clear();
        }
        history.add(cmd);
        cursor++;

        lastResult = cmd.execute();
        return ResultDto.builder().result(lastResult).build();
    }

    public ResultDto undo() {
        if (cursor == 0) {
            throw new IllegalStateException("Nothing to undo");
        }
        OperationCommand lastCommand = history.get(--cursor);
        lastCommand.undo();

        lastResult = (cursor == 0)
                ? BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)
                : history.get(cursor - 1).getResult();

        return ResultDto.builder().result(lastResult).build();
    }

    public ResultDto redo() {
        if (cursor == history.size()) {
            throw new IllegalStateException("Nothing to redo");
        }
        OperationCommand cmd = history.get(cursor++);
        lastResult = cmd.execute();

        return ResultDto.builder().result(lastResult).build();
    }

    public BigDecimal current() {
        return lastResult;
    }
}
