public class JoinQueueEvent extends Event {
  private Customer customer;
  private Shop shop;

  public JoinQueueEvent(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d joined queue %s", this.customer.getCustomerId(), 
        this.shop.getCustomerQueue().toString());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    if (shop.getCustomerQueue().isFull()) {
      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer, this.shop)
      };
    } else {
      shop.addCustomerToQueue(this.customer);
      return new Event[] {};
    }
  }
}
