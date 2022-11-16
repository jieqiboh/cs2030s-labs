package cs2030s.fp;

/**
 * Represent a function that may store a memoized or uninitialised value.
 * CS2030S Lab 6
 * AY22/23 Semester 1
 * @author Boh Jie Qi
 * @param <T> The type of the Memo instance.
 */

public class Memo<T> extends Lazy<T> {
  /**
   * The initialised value is stored here and reused in further calls to {@code get()}.
   */
  private Actually<T> value;

  /**
   * Overloaded constructor for initialised v.
   * @param v Initialised v
   */
  protected Memo(T v) {
    super(() -> v);
    this.value = Actually.ok(v);
  }

  /**
   * Overloaded constructor for uninitialised c.
   * @param c stored in superclass' init field
   */
  protected Memo(Constant<? extends T> c) {
    super(c);
    this.value = Actually.err(null);
  }

  /**
   * Factory method for uninitialised c.
   * @param c Constant c
   * @param <T> Type param
   * @return {@code Memo<T>} object
   */
  public static <T> Memo<T> from(Constant<? extends T> c) {
    return new Memo<T>(c);
  }

  /**
   * 2nd factory method for initialised c.
   * @param v initialised value stored in this.value
   * @param <T> Type param for Memo object.
   * @return Memo object
   */
  public static <T> Memo<T> from(T v) {
    return new Memo<T>(v);
  }

  /**
   * Gets the value stored.
   * @return encapsulated value
   */
  @Override
  public T get() {
    T res = this.value.except(super::get);
    this.value = Actually.ok(res);
    return res;
  }

  /**
   * Gets string representation of value.
   * @return this.value as a string or "?"
   */
  @Override
  public String toString() {
    return this.value.transform(x -> x.toString()).unless("?");
  }

  /**
   * Chains the invoke of immutator and returns a new 
   * {@code Memo<R>} encapsulating the new Constant.
   * @param f The immutator.
   * @param <R> The type that this is transformed to.
   * @return {@code Memo<R>} encapsulating Constant after chaining invoke() of Immutator.
   */
  @Override
  public <R> Memo<R> transform(Immutator<? extends R, ? super T> f) {
    return new Memo<R>(() -> f.invoke(this.get()));
  }

  /**
   * Chains the invoke of immutator and returns a new
   * {@code Memo<R>} encapsulating the new Constant.
   * @param i The immutator.
   * @param <R> The type that this is transformed to
   * @return {@code Memo<R>} encapsulating Constant after chaining invoke() of Immutator.
   */
  @Override
  public <R> Memo<R> next(Immutator<? extends Lazy<? extends R>, ? super T> i) {
    return new Memo<R>(() -> i.invoke(this.get()).get());
  }

  /**
   * Takes in a Combiner that performs an operation on this and s and 
   * returns the new {@code Memo<R>}.
   * @param s {@code Memo<R>} object
   * @param c Combiner object
   * @param <R> return type parameter
   * @param <S> type parameter of S
   * @return {@code Memo<R>} object
   */
  public <R, S> Memo<R> combine(Memo<? extends S> s,
      Combiner<? extends R, ? super T, ? super S> c) {
    return new Memo<R>(() -> c.combine(this.get(), s.get()));
  }
}
