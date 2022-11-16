public class JoinCounterQueueEvent extends Event {
  private Customer customer;
  private Shop shop;
  private Counter counter;

  public JoinCounterQueueEvent(double time, Customer customer, Shop shop, Counter counter) {
    super(time);
    this.customer = customer;
    this.shop = shop;
    this.counter = counter;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d joined counter queue (at S%s %s)",
        this.customer.getCustomerId(), this.counter.getCounterId(), this.counter.queueToString());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    counter.hold(this.customer);
    return new Event[] {};
  }
}
