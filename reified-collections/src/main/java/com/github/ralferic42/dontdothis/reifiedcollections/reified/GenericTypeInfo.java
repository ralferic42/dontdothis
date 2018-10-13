package com.github.ralferic42.dontdothis.reifiedcollections.reified;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Scanner/Result-Container for generic type arguments, usable only after construction of an instance of an
 * anonymous class
 */
public class GenericTypeInfo {

  private final ParameterizedType parameterizedType;
  private final List<Class<?>> rawTypes;

  public GenericTypeInfo(Object createdInstance) {
    // scan for actual type arguments...
    // ...type information is accessible via the generic superclass
    Type genericSuperclass = createdInstance.getClass().getGenericSuperclass();
    if (!(genericSuperclass instanceof ParameterizedType)) {
      this.parameterizedType = null;
      this.rawTypes = Collections.emptyList();
    }
    else {
      this.parameterizedType = (ParameterizedType) genericSuperclass;
      List<Class<?>> resultList = new ArrayList<>();
      Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
      for (Type t : actualTypeArguments) {
        if (t instanceof ParameterizedType) {
          resultList.add( (Class<?>) (((ParameterizedType) t).getRawType()));
        }
        else {
          resultList.add((Class<?>) t);
        }
      }
      this.rawTypes = Collections.unmodifiableList(resultList);
    }

  }

  public ParameterizedType getParameterizedType() {
    return parameterizedType;
  }

  public List<Class<?>> getRawTypes() {
    return rawTypes;
  }

}
