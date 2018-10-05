package com.github.ralferic42.dontdothis.reifiedcollections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.UnaryOperator;

/**
 * derivation of {@link ArrayList} with type checking in all state-modifying methods
 * @param <E> Element Type
 */
public abstract class ReifiedArrayList<E>
extends ArrayList<E> {

  protected final class ValidatingOperator
  implements UnaryOperator<E> {
    private final UnaryOperator<E> wrappedOperator;

    public ValidatingOperator(UnaryOperator<E> wrappedOperator) {
      this.wrappedOperator = wrappedOperator;
    }

    @Override
    public E apply(E t) {
      return validateElement( wrappedOperator.apply(t) );
    }

  }

  public ReifiedArrayList(int initialCapacity) {
    super(initialCapacity);
  }

  public ReifiedArrayList() {
    super();
  }

  public ReifiedArrayList(Collection<? extends E> c) {
    // TODO: initialize validation here
    super.addAll( validateCollection(c) );
  }

  /**
   * validation of a single element, this method is called from inside the constructor and must be final
   */
  protected final E validateElement(E elementToCheck ) {
    return elementToCheck;
  }

  /**
   * validation of a collection of elements, this method is called from inside the constructor and must be final
   */
  protected final Collection<? extends E> validateCollection( Collection<? extends E> collectionToCheck ) {
    return collectionToCheck;
  }

  @Override
  public E set(int index, E element) {
    return super.set(index, validateElement(element));
  }

  @Override
  public boolean add(E e) {
    return super.add(validateElement(e));
  }

  @Override
  public void add(int index, E element) {
    super.add(index, validateElement(element));
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    return super.addAll(validateCollection(c));
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    return super.addAll(index, validateCollection(c));
  }

  @Override
  public void replaceAll(UnaryOperator<E> operator) {
    super.replaceAll( new ValidatingOperator(operator) );
  }

}
