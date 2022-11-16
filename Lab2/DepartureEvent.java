public class DepartureEvent extends Event {
  private Customer customer;
  private Shop shop;

  public DepartureEvent(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d departed", this.customer.getCustomerId());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}

