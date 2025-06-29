package lottery.model;

import lombok.Builder;

import java.util.List;

@Builder
public class MultiDrawResponse {
    private String userId;
    private List<String> results;

    public MultiDrawResponse() {}

    public MultiDrawResponse(String userId, List<String> results) {
        this.userId = userId;
        this.results = results;
    }

    public String getUserId() { return userId; }
    public List<String> getResults() { return results; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setResults(List<String> results) { this.results = results; }
}
