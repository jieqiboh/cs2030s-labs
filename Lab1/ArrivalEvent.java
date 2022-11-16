public class ArrivalEvent extends Event {
    private Customer customer;
    /**
     * Creates an event that occurs at the given time.
     *
     * @param time The time the event occurs.
     * @param customer The customer that arrived
     */
    public ArrivalEvent(double time, Customer customer) {
        super(time);
        this.customer = customer;
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format(": Customer %d arrives", this.customer.getCustomerId());
        return super.toString() + str;
    }

    @Override
    public Event[] simulate() {
        //customer searches for counter
        Counter counter = ShopSimulation.getShop().findFirstAvailable();
        if (counter==null) {
            //no such counter found, customer should depart
            return new Event[] {
                    new DepartureEvent(this.getTime(),customer)
            };
        } else {
            return new Event[] {
                    new ServiceBeginEvent(this.getTime(),customer,counter)
            };
        }

    }
}

