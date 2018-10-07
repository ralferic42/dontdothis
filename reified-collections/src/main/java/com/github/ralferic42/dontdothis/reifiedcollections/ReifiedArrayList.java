package com.github.ralferic42.dontdothis.reifiedcollections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * drop-in replacement for {@link ArrayList} with type checking in all state-modifying methods
 * This should have been implemented as a decorator, but that would no longer be a drop-in replacement
 * for ArrayList. A decorator would have been protected against extensions of the base class.
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

  private final Class<?> classToCheckAgainst;

  public ReifiedArrayList(int initialCapacity) {
    super(initialCapacity);
    this.classToCheckAgainst = getTypeArgument();
  }

  public ReifiedArrayList() {
    super();
    this.classToCheckAgainst = getTypeArgument();
  }

  public ReifiedArrayList(Collection<? extends E> c) {
    super.addAll( validateCollection(c) );
    this.classToCheckAgainst = getTypeArgument();
  }

  private final Class<?> getTypeArgument() {
    List<Class<?>> typeArguments = new GenericTypeScanner().scanForGenericTypes(this);
    if (typeArguments.size()==1) {
      return typeArguments.get(0);
    }
    else {
      throw new IllegalArgumentException("this class is currently limited to one type Argument only");
    }
  }

  protected Class<?> getClassToCheckAgainst() {
    return classToCheckAgainst;
  }

  /**
   * validation of a single element, this method is called from inside the constructor and must be final
   */
  protected final E validateElement(E elementToCheck ) {
    if (classToCheckAgainst!=null && !classToCheckAgainst.isInstance(elementToCheck)) {
      throw new IllegalArgumentException( "new element is not an instance of "+classToCheckAgainst.getName());
    }
    return elementToCheck;
  }

  /**
   * validation of a collection of elements, this method is called from inside the constructor and must be final
   */
  protected final Collection<? extends E> validateCollection( Collection<? extends E> collectionToCheck ) {
    if (classToCheckAgainst!=null) {
      for (E newElement : collectionToCheck) {
        if (!classToCheckAgainst.isInstance(newElement)) {
          throw new IllegalArgumentException( "new element is not an instance of "+classToCheckAgainst.getName());
        }
      }
    }
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
