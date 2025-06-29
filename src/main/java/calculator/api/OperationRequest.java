package calculator.api;

import calculator.model.OperationType;

import java.math.BigDecimal;

public class OperationRequest {
    private BigDecimal left;
    private BigDecimal right;
    private OperationType operation;

    public OperationRequest() { }

    public OperationRequest(BigDecimal left, BigDecimal right, OperationType operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    public BigDecimal getLeft() {
        return left;
    }

    public void setLeft(BigDecimal left) {
        this.left = left;
    }

    public BigDecimal getRight() {
        return right;
    }

    public void setRight(BigDecimal right) {
        this.right = right;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }
}
