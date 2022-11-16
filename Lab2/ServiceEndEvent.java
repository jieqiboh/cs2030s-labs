public class ServiceEndEvent extends Event {
  private Customer customer;
  private Counter counter;
  private Shop shop;

  public ServiceEndEvent(double time, Customer customer, Shop shop, Counter counter) {
    super(time);
    this.customer = customer;
    this.shop = shop;
    this.counter = counter;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d service done (by S%d)",
        this.customer.getCustomerId(), this.counter.getCounterId());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    // The current event is a service-end event.
    // Mark the counter is available, then schedule
    // a departure event at the current time.
    double time = this.getTime();
    this.counter.endUse();
    if (shop.getCustomerQueue().isEmpty()) {
      return new Event[] {
        new DepartureEvent(time, this.customer, this.shop)
      };
    } else {
      //get firstAvailableCounter and deq a customer
      //checks for available counter and queue empty are done
      Counter counter = this.shop.findFirstAvailable();
      Customer customer = this.shop.getCustomerInQueue();
      return new Event[] {
        new DepartureEvent(time, this.customer, this.shop),
        new ServiceBeginEvent(time, customer, this.shop, counter) 
      };
    }
  }
}

