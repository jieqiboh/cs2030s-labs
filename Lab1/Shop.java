public class Shop {
    private Counter[] allCounters;

    public Shop(Counter[] allCounters) {
        this.allCounters = allCounters;
    }

    //from all counters, find the first available one
    //Methods relating to both customer and counter are located here
    public Counter findFirstAvailable() {
        for (Counter c: allCounters) {
            if (c.isAvailable()) return c;
        }
        return null;
    }

}

