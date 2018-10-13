package com.github.ralferic42.dontdothis.reifiedcollections.reified;

/**
 * key used by {@link TypedMap} which provides a reference to the stored type
 */
public abstract class TypedMapKey<T> {

  private final GenericTypeInfo genericTypeInfo;
  private final Class<T> classToCast;

  public TypedMapKey() {
    this.genericTypeInfo = new GenericTypeInfo(this);
    if (genericTypeInfo.getRawTypes().size()!=1) {
      throw new IllegalArgumentException("can only operate on exactly one generic type parameter");
    }
    classToCast = (Class<T>) genericTypeInfo.getRawTypes().get(0);
  }

  public T cast(Object o) {
    return classToCast.cast(o);
  }

}
