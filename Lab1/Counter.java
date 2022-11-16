public class Counter {
    private int counterId;
    private boolean available;

    public Counter(int counterId, boolean available) {
        this.counterId = counterId;
        this.available = available;
    }

    public void use() {
        this.available = false;
    }
    public void endUse() {
        this.available = true;
    }
    public boolean isAvailable() {
        return this.available;
    }

    public int getCounterId() {
        return counterId;
    }
}

