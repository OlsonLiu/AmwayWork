package lottery.model;

public class DrawResponse {
    private String userId;
    private String result;

    public DrawResponse() {}

    public DrawResponse(String userId, String result) {
        this.userId = userId;
        this.result = result;
    }

    public String getUserId() { return userId; }
    public String getResult() { return result; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setResult(String result) { this.result = result; }
}