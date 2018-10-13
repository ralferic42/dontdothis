package com.github.ralferic42.dontdothis.reifiedcollections;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TypedMapKey)) return false;
    TypedMapKey<?> that = (TypedMapKey<?>) o;
    return Objects.equals(classToCast, that.classToCast);
  }

  @Override
  public int hashCode() {
    return Objects.hash(classToCast);
  }

}
