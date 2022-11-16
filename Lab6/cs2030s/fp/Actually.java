/**
 * CS2030S Lab 6
 * AY22/23 Semester 1
 *
 * @author Boh Jie Qi 14A
 */

package cs2030s.fp;

public abstract class Actually<T> implements Immutatorable<T>, Actionable<T>{

  public static <T> Actually<T> ok(T res) {
    return new Success<T>(res);    
  }

  @SuppressWarnings("unchecked")
  public static <T> Actually<T> err(Exception exception) {
    return (Actually<T>) new Failure(exception);
  }

  public abstract T unwrap() throws Exception;

  public abstract <S extends T> T except(Constant<? extends S> c);

  public abstract void finish(Action<? super T> a);

  public abstract <S extends T> T unless(S x);

  public abstract <R> Actually<R> next(Immutator<? extends Actually<? extends R>, ? super T> i);
  
  public abstract <R> Actually<R> transform(Immutator<? extends R, ? super T> f);

  static final class Success<T> extends Actually<T> {
    private final T value;

    private Success(T value) {
      this.value = value;
    }

    @Override
    public String toString() {
      if (this.value == null) {
        return "<>";
      } else {
        return "<" + this.value.toString() + ">";
      }
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Success<?>) {
        Success<?> some = (Success<?>) obj;
        if (this.value == some.value) {
          return true;
        }
        if (this.value == null || some.value == null) {
          return false;
        }
        return this.value.equals(some.value);
      }
      return false;
    }

    @Override
    public T unwrap() {
      return this.value; 
    }

    @Override
    public <S extends T> T except(Constant<? extends S> c) {
      return this.value;
    }
    
    @Override
    public void finish(Action<? super T> a) {
      a.call(this.value);
    }

    @Override
    public <S extends T> T unless(S x) {
      return this.value;
    }

    @Override
    public <R> Actually<R> transform(Immutator<? extends R, ? super T> i) {
      try {
        return Actually.ok(i.invoke(this.value));
      } catch(Exception e) {
        return Actually.err(e);
      }
    }

    @Override
    public void act(Action<? super T> action) {
      action.call(this.value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> Actually<R> next(Immutator<? extends Actually<? extends R>, ? super T> i) {
      try {
        return (Actually<R>) i.invoke(this.value);
      } catch (Exception e) {
        return Actually.err(e);
      }
    }
  }

  static final class Failure extends Actually<Object> {
    private final Exception exc;

    private Failure(Exception exc) {
      this.exc = exc;
    }

    @Override
    public String toString() {
      return "[" + this.exc.getClass().getName() + "]" + " " + this.exc.getMessage();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Failure) {
        Failure some = (Failure) obj;
        if (this.exc.getMessage() == null || some.exc.getMessage() == null) {
          return false;
        }
        if (this.exc.getMessage() == some.exc.getMessage()) {
          return true;
        }
      }
      return false;
    }

    @Override
    public Object unwrap() throws Exception {
      throw(this.exc); 
    }

    @Override
    public <T> T except(Constant<? extends T> c) {
      return c.init();
    }

    @Override
    public void finish(Action<? super Object> a) {
    }

    @Override
    public <U> U unless(U x) {
      return x;
    }

    @Override
    public <R> Actually<R> transform(Immutator<? extends R, ? super Object> i) {
      return Actually.err(this.exc);
    };

    @Override
    public void act(Action<? super Object> action) {
    }

    @Override
    public <R> Actually<R> next(Immutator<? extends Actually<? extends R>, ? super Object> i) {
      return Actually.err(this.exc);
    }
  }

}
