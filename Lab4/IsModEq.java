/**
 * A non-generic Immutator with parameter
 * div and mod that takes in an integer val
 * and return the boolean value by checking
 * if the given value is equal to mod when
 * divided by div.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Put Your Name (Lab Group)
 */
class IsModEq implements Immutator<Boolean, Integer> {
  private int div;
  private int check;

  public IsModEq(int div, int check) {
    this.div = div;
    this.check = check;
  }

  @Override
  public Boolean invoke(Integer val) {
    if (this.div == 0) {
      throw new ArithmeticException("Cannot divide by 0!");
    }
    if ((val % this.div) == check) {
      return true;
    }
    return false;
  }
}
