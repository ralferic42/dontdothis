package com.github.ralferic42.dontdothis.reifiedcollections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.UnaryOperator;

public abstract class ReifiedArrayList<E>
extends ArrayList<E> {

  protected class ValidatingOperator
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

  protected E validateElement(E elementToCheck ) {
    return elementToCheck;
  }

  protected Collection<? extends E> validateCollection( Collection<? extends E> collectionToCheck ) {
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
