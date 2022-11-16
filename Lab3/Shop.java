public class Shop {
  private Array<Counter> allCounters;
  private Queue shopQueue;

  public Shop(int numOfCounters, int numShopQueueLength, int numCounterQueueLength) {
    //initialise counters
    Array<Counter> allCounters = new Array<>(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      allCounters.set(i, new Counter(true, numCounterQueueLength));
    }

    this.allCounters = allCounters;

    this.shopQueue = new Queue(numShopQueueLength);
  }

  //from all counters, find the first available one
  //Methods relating to both customer and counter are located here
  //Need to perform checks after calling this method to make sure that the counter returned is !null
  public Counter getAvailableCounter() {
    for (int i = 0; i < allCounters.size(); i++) {
      Counter c = allCounters.get(i);
      if (c.isAvailable()) {
        return c;
      }
    }
    return null;
  }

  //get the Counter with min Queue
  public Counter getMinQueueCounter() {
    //to get allCounters and get min() counter from there
    return allCounters.min();
  }

  public Array<Counter> getAllCounters() {
    return this.allCounters;
  }

  //Mtds relating to Queue

  public Customer release() {
    return (Customer) this.shopQueue.deq();
  }

  public boolean hold(Customer c) {
    //only called if counters all full and ShopQueue is not full
    return this.shopQueue.enq(c);
  }

  public boolean queueIsFull() {
    return shopQueue.isFull();
  }

  public boolean queueIsEmpty() {
    return shopQueue.isEmpty();
  }

  public int queueLength() {
    return shopQueue.length();
  }

  public String queueToString() {
    return this.shopQueue.toString();
  }
}

