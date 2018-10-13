package com.github.ralferic42.dontdothis.reifiedcollections.typeserased;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TypesErasedMap {
  private final Map<TypesErasedMapKey<?>, Object> innerMap;

  public TypesErasedMap() {
    this.innerMap = new HashMap<>();
  }

  public <T> T get(TypesErasedMapKey<T> key) {
    Object content = innerMap.get(key);
    return key.cast(content);
  }

  public <T> T put(TypesErasedMapKey<T> key, T value) {
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

  public Set<TypesErasedMapKey<?>> keySet() {
    return innerMap.keySet();
  }

  public Collection<Object> values() {
    return innerMap.values();
  }

  public Set<Map.Entry<TypesErasedMapKey<?>, Object>> entrySet() {
    return innerMap.entrySet();
  }

}
