public class Shop {
  private Counter[] allCounters;
  private CustomerQueue customerQueue;

  public Shop(int numOfCounters, int numQueueLength) {
    //initialise counters
    Counter[] allCounters = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      allCounters[i] = new Counter(i, true);
    }

    this.allCounters = allCounters;

    this.customerQueue = new CustomerQueue(numQueueLength);
  }

  //from all counters, find the first available one
  //Methods relating to both customer and counter are located here
  //Need to perform checks after calling this method to make sure that the counter returned is !null

  public Counter findFirstAvailable() {
    for (Counter c : allCounters) {
      if (c.isAvailable()) {
        return c;
      }
    }
    return null;
  }

  public CustomerQueue getCustomerQueue() {
    return this.customerQueue; 
  }

  //checks for isEmpty are a redundancy since Event classes calling this mtd also do checks
  public Customer getCustomerInQueue() { 
    if (this.customerQueue.isEmpty()) {
      return null;
    } else {
      return (Customer) this.customerQueue.deq();
    }
  }

  //checks for isEmpty are a redundancy but are included just to be safe
  public boolean addCustomerToQueue(Customer customer) { 
    if (this.customerQueue.isFull()) {
      return false;
    } else {
      this.customerQueue.enq(customer);
      return true;
    }
  }
}

