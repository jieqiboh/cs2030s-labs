public class JoinShopQueueEvent extends Event {
  private Customer customer;
  private Shop shop;

  public JoinShopQueueEvent(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d joined shop queue %s", this.customer.getCustomerId(), 
        this.shop.queueToString());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() { //Checks if ShopQueue is full alr handled in ArrivalEvent.java
    shop.hold(this.customer);
    return new Event[] {};
  }
}
