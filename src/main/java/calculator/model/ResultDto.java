package calculator.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
public class ResultDto {
    private BigDecimal result;

    public ResultDto() { }

    public ResultDto(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
