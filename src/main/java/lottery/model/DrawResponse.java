package lottery.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class DrawResponse {
    private String userId;
    private String result;
}