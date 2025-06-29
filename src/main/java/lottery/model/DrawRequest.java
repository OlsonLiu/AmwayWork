package lottery.model;

public class DrawRequest {
    private String userId;
    private int count = 1;

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
}