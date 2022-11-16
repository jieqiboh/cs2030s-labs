/**
 * CS2030S Lab 5
 * AY22/23 Semester 1
 *
 * @author Boh Jie Qi 14A
 */

package cs2030s.fp;

public abstract class Transformer<R,P> implements Immutator<R,P> {
    public <N> Transformer<R,N> after(Transformer<P,N> t) {
        Transformer<R,P> f = this; //temporary var to access this
        return new Transformer<R,N>() {
            @Override
            public R invoke(N param) {
                return f.invoke(t.invoke(param));
            }
        };
    }

    public <T> Transformer<T,P> before(Transformer<T,R> t) {
        Transformer<R,P> f = this; //temporary var to access this
        return new Transformer<T,P>() {
            @Override
            public T invoke(P param) {
                return t.invoke(f.invoke(param));
            }
        };
    }
}
