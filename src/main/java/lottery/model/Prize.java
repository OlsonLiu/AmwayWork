package lottery.model;

import lombok.Builder;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

@ToString
public class Prize {
    private final String name;
    private final double probability;
    private final AtomicInteger remaining;

    public Prize(String name, double probability, int quantity) {
        this.name = name;
        this.probability = probability;
        this.remaining = new AtomicInteger(quantity);
    }

    public String getName() {
        return name;
    }

    public double getProbability() {
        return probability;
    }

    public int getRemaining() {
        return remaining.get();
    }

    public boolean tryDecrement() {
        while (true) {
            int current = remaining.get();
            if (current <= 0) return false;
            if (remaining.compareAndSet(current, current - 1)) return true;
        }
    }
}