package com.github.ralferic42.dontdothis.reifiedcollections.reified;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Map-like container using different types for each value. {@link TypedMapKey} is used as a key and
 * provides a reference to the desired type. This is definitely not a drop-in replacement for a standard
 * implementation of {@link Map}. It does not and can not offer a large part of the Map-capabilities.
 */
public class TypedMap {
  private final Map<TypedMapKey<?>,Object> innerMap;

  public TypedMap() {
    this.innerMap = new HashMap<>();
  }

  public <T> T get(TypedMapKey<T> key) {
    Object content = innerMap.get(key);
    return key.cast(content);
  }

  public <T> T put(TypedMapKey<T> key, T value) {
    T verifiedContent = key.cast(value);
    Object previousContent = innerMap.put(key, verifiedContent);
    return key.cast(previousContent);
  }

  public int size() {
    return innerMap.size();
  }

  public boolean isEmpty() {
    return innerMap.isEmpty();
  }

  public boolean containsKey(Object key) {
    return innerMap.containsKey(key);
  }

  public boolean containsValue(Object value) {
    return innerMap.containsValue(value);
  }

  public void clear() {
    innerMap.clear();
  }

  public Set<TypedMapKey<?>> keySet() {
    return innerMap.keySet();
  }

  public Collection<Object> values() {
    return innerMap.values();
  }

  public Set<Map.Entry<TypedMapKey<?>, Object>> entrySet() {
    return innerMap.entrySet();
  }

}
