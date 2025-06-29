package calculator.model;

import java.math.BigDecimal;

public interface Command {
    BigDecimal execute();
    BigDecimal undo();
}
