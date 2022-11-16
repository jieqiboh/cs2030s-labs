public class ServiceEndEvent extends Event {
    private Customer customer;
    private Counter counter;
    public ServiceEndEvent(double time, Customer customer, Counter counter) {
        super(time);
        this.customer = customer;
        this.counter = counter;
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format(": Customer %d service done (by Counter %d)",
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
        return new Event[] {
                new DepartureEvent(time,this.customer)
        };
    }
}

