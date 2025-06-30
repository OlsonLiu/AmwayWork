package lottery.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
@Builder
public class StatsResponse {
    private Map<String, Integer> remaining;
    private Set<String> participants;
}