/**
 * A non-generic Action to print the String
 * representation of the object.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Put Your Name (Lab Group)
 */
public class Print<T> implements Action<T> {
  @Override
  public void call(T arg) {
    if (arg == null) {
      return;
    } else {
      System.out.println(arg.toString());
    }
  }
}
