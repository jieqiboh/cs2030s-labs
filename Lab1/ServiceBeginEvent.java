public class ServiceBeginEvent extends Event {
    private Customer customer;
    private Counter counter;

    public ServiceBeginEvent(double time, Customer customer, Counter counter) {
        super(time);
        this.customer = customer;
        this.counter = counter;
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format(": Customer %d service begin (by Counter %d)",
                this.customer.getCustomerId(), this.counter.getCounterId());
        return super.toString() + str;
    }

    @Override
    public Event[] simulate() {
        // The current event is a service-begin event.
        // Mark the counter is unavailable, then schedule
        // a service-end event at the current time + service time.
        double time = this.getTime();
        this.counter.use();
        double endTime = time + this.customer.getServiceTime();
        return new Event[] {
                new ServiceEndEvent(endTime,this.customer,this.counter)
        };
    }

}

