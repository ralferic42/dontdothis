package com.github.ralferic42.dontdothis.reifiedcollections.typeserased;

public class TypesErasedMapKey<T> {
  public T cast(Object rawValue) {
    return (T) rawValue;
  }
}
