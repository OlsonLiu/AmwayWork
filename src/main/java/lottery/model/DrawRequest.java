package lottery.model;

import lombok.Getter;

@Getter
public class DrawRequest {
    private String userId;
    private int count = 1;
}