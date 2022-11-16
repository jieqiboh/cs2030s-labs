package cs2030s.fp;

/**
 * Represent a function to initialise a constant value.
 * CS2030S Lab 6
 * AY22/23 Semester 1
 * @author Boh Jie Qi
 * @param <T> The type of the Lazy instance.
 */

public class Lazy<T> implements Immutatorable<T> {
  
  /**
   * The uninitialised value of Lazy.
   */
  private Constant<? extends T> init;
  
  /**
   * Takes in a Constant that produces the value when needed.
   * @param c The uninitialised Constant value 
   */
  protected Lazy(Constant<? extends T> c) {
    this.init = c;
  }
  
  /**
   * Takes in a Constant, then calls constructor.
   * @param c The uninitialised Constant value 
   * @param <T> The type of the Lazy instance
   * @return A Lazy instance
   */
  public static <T> Lazy<T> from(Constant<? extends T> c) {
    return new Lazy<T>(c);
  }

  /**
   * Takes in an already initialised value and stores it in Constant.
   * @param v given value of type T
   * @param <T> The type of the Lazy instance
   * @return Lazy returned Lazy object
   */
  public static <T> Lazy<T> from(T v) {
    return new Lazy<T>(() -> v);
  }

  /**
   * Computes and returns the value.
   * @return T value return type
   */
  public T get() {
    return init.init();
  }

  /**
   * Returns string representation.
   * @return String
   */
  @Override
  public String toString() {
    return this.get().toString();
  }

  /**
   * Transforms the value in Lazy.
   * @param f The immutator.
   * @return Lazy with invoke chained to this.get()
   * @param <R> Type parameter of return type
   */
  @Override
  public <R> Lazy<R> transform(Immutator<? extends R, ? super T> f) {
    return new Lazy<>(() -> f.invoke(this.get()));
  }

  /**
   * Invokes invoke() on this.get() and returns the new value in a {@code Lazy<R>} object
   * @param i Immutator
   * @return {@code Lazy<R>} encapsulating the new this.value
   * @param <R> Type that this is transformed to.
   */
  @SuppressWarnings("unchecked")
  public <R> Lazy<R> next(Immutator<? extends Lazy<? extends R>, ? super T> i) {
    return (Lazy<R>) i.invoke(this.get());
  }

}


