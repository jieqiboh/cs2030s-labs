/**
 * The Immutatorable interface that can
 * transform when given something that is
 * Immutator.
 *
 * Contains a single abstract method transform.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Put Your Name (Lab Group)
 */

package cs2030s.fp;

public interface Immutatorable<T> {
  <R> Immutatorable<R> transform(Immutator<? extends R, ? super T> i);  
}

