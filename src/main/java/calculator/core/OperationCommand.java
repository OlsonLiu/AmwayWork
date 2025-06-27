package calculator.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OperationCommand implements Command {
    private final BigDecimal left;
    private final BigDecimal right;
    private final OperationType type;
    private BigDecimal result;

    public OperationCommand(BigDecimal left, BigDecimal right, OperationType type) {
        this.left = left.setScale(6, RoundingMode.HALF_UP);
        this.right = right.setScale(6, RoundingMode.HALF_UP);
        this.type = type;
    }

    @Override
    public BigDecimal execute() {
        switch (type) {
            case ADD -> result = left.add(right);
            case SUBTRACT -> result = left.subtract(right);
            case MULTIPLY -> result = left.multiply(right);
            case DIVIDE -> {
                if (right.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = left.divide(right, 6, RoundingMode.HALF_UP);
            }
        }
        System.out.println("left:"+left+", right:"+right+", result:"+result);
        return result.setScale(6, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal undo() {
        System.out.println("udo left:"+left+", right:"+right+", result:"+result);
        return left;
    }

    public BigDecimal getResult() {
        return result.setScale(6, RoundingMode.HALF_UP);
    }
}
