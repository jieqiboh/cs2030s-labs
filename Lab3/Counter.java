public class Counter implements Comparable<Counter> {
  private int counterId = 0;
  private static int lastId = 0;
  private boolean available;
  private Queue counterQueue;

  public Counter(boolean available, int maxLength) {
    this.counterId = Counter.lastId;
    this.available = available;
    this.counterQueue = new Queue(maxLength);
    Counter.lastId++;
  }

  public void use() {
    this.available = false;
  }

  public void endUse() {
    this.available = true;
  }

  public boolean isAvailable() {
    return this.available;
  }

  public int getCounterId() {
    return counterId;
  }

  //Methods relating to counterQueue start here

  public boolean hold(Customer c) {
    return counterQueue.enq(c);
  }

  public Customer release() {
    return (Customer) counterQueue.deq();
  }

  public boolean queueIsFull() {
    return this.counterQueue.isFull();
  }

  public boolean queueIsEmpty() {
    return this.counterQueue.isEmpty();
  }

  public String queueToString() {
    return this.counterQueue.toString();
  }
  
  @Override
  public int compareTo(Counter c) {
    int qsize = this.counterQueue.length();
    int cqsize = c.counterQueue.length();
    int id = this.counterId;
    int cid = c.counterId;
    if (qsize > cqsize) {
      return 1;
    } else if (cqsize > qsize) {
      return -1;
    } else {
      if (id > cid) {
        return 1;
      } else if (cid > id) {
        return -1;
      } else {
        return 0;
      }
    }
  }
}

