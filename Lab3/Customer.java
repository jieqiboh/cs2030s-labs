public class Customer {
  private int customerId = 0;
  private static int lastId = 0;
  private double arrivalTime;
  private double serviceTime;

  public Customer(double arrivalTime, double serviceTime) {
    this.customerId = Customer.lastId;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    Customer.lastId++;
  }

  @Override
  public String toString() {
    return "C" + customerId;
  }

  //method to use counter? From there can use serviceTime
  //need getters since toString needs to access these values

  public int getCustomerId() {
    return customerId;
  }

  public double getArrivalTime() {
    return arrivalTime;
  }

  public double getServiceTime() {
    return serviceTime;
  }
}

