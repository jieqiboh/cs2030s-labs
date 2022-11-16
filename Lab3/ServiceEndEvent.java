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
    str = String.format(": C%d service done (by S%d %s)",
        this.customer.getCustomerId(), this.counter.getCounterId(), this.counter.queueToString());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    // If cq.isEmpty(), depart
    // else, deq a customer from cq
    // if !sq.isEmpty()
    // deq a customer from sq, then depart, joinCounterQueue and servicebegin
    // else
    // depart and servicebegin for customer from cq

    this.counter.endUse();

    if (this.counter.queueIsEmpty()) {
      if (!this.shop.queueIsEmpty()) {
        Customer sqCustomer = this.shop.release();
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer, this.shop),
          new ServiceBeginEvent(this.getTime(), sqCustomer, this.shop, this.counter)
        };
      } else {
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer, this.shop)
        };
      }
    } else {
      Customer cqCustomer = this.counter.release();

      if (!this.shop.queueIsEmpty()) {
        Customer sqCustomer = this.shop.release();
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer, this.shop),
          new ServiceBeginEvent(this.getTime(), cqCustomer, this.shop, this.counter),
          new JoinCounterQueueEvent(this.getTime(), sqCustomer, this.shop, this.counter),
        };
      } else {
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer, this.shop),
          new ServiceBeginEvent(this.getTime(), cqCustomer, this.shop, this.counter)
        };
      }
    }
  }
}

