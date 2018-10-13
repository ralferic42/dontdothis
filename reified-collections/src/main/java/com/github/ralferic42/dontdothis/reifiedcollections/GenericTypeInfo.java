package com.github.ralferic42.dontdothis.reifiedcollections;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Scanner for generic type arguments, usable only after construction of an instance of an anonymous class
 */
public class GenericTypeInfo {

  public List<Class<?>> scanForGenericTypes(Object createdInstance) {
    List<Class<?>> resultList = new ArrayList<>();
    // scan for actual type arguments...
    // ...type information is accessible via the generic superclass
    Type genericSuperclass = createdInstance.getClass().getGenericSuperclass();
    if (genericSuperclass instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
      Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
      for (Type t : actualTypeArguments) {
        if (t instanceof ParameterizedType) {
          resultList.add( (Class<?>) (((ParameterizedType) t).getRawType()));
        }
        else {
          resultList.add((Class<?>) t);
        }
      }
    }
    return resultList;
  }

}
