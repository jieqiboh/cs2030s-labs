public class DepartureEvent extends Event {
    private Customer customer;

    public DepartureEvent(double time, Customer customer) {
        super(time);
        this.customer = customer;
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format(": Customer %d departed", this.customer.getCustomerId());
        return super.toString() + str;
    }

    @Override
    public Event[] simulate() {
        return new Event[] {};
    }
}

