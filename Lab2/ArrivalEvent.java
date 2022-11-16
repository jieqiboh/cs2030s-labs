public class ArrivalEvent extends Event {
  private Customer customer;
  private Shop shop;

  /**
   * Creates an event that occurs at the given time.
   *
   * @param time The time the event occurs.
   * @param customer The customer that arrived
   * @param shop Shop instance in ShopSimulation
   */

  public ArrivalEvent(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d arrived %s", this.customer.getCustomerId(),
        this.shop.getCustomerQueue().toString());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    //customer searches for counter
    Counter counter = shop.findFirstAvailable();
    if (counter == null) {
      //no such counter found 
      //if customerQueue is full, depart
      //else joinqueue
      if (shop.getCustomerQueue().isFull()) {
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer, this.shop)
        };

      } else {
        return new Event[] {
          new JoinQueueEvent(this.getTime(), this.customer, this.shop)
        };
      }
    } else {
      return new Event[] {
        new ServiceBeginEvent(this.getTime(), this.customer, this.shop, counter)
      };
    }

  }
}

