public class Customer {
  private int customerId;
  private double arrivalTime;
  private double serviceTime;

  public Customer(int customerId, double arrivalTime, double serviceTime) {
    this.customerId = customerId;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
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

