/**
 * The Array<T> for CS2030S 
 *
 * @author JieQi
 * @version CS2030S AY21/22 Semester 2
 */
class Array<T extends Comparable<T>> { // TODO: Change to bounded type parameter
  private T[] array;

  Array(int size) {
    // @SuppressWarnings("unchecked")
    @SuppressWarnings("rawtypes")
    T[] a = (T[]) new Comparable[size];
    this.array = a;
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public int size() {
    return this.array.length;
  }

  public T min() {
    T min = this.array[0];
    for (T i : this.array) {
      if (i.compareTo(min) < 0) {
        min = i;
      }
    }
    return min;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
