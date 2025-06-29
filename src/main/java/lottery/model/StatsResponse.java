package lottery.model;

import java.util.Map;

public class StatsResponse {
    private Map<String, Integer> remaining;
    private long participants;

    public StatsResponse(Map<String, Integer> remaining, long participants) {
        this.remaining = remaining;
        this.participants = participants;
    }

    public Map<String, Integer> getRemaining() { return remaining; }
    public long getParticipants() { return participants; }
    public void setRemaining(Map<String, Integer> remaining) { this.remaining = remaining; }
    public void setParticipants(long participants) { this.participants = participants; }
}