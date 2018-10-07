package com.github.ralferic42.dontdothis.reifiedcollections;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Scanner for generic type arguments used during construction of an anonymous class
 */
public class GenericTypeScanner {

  public List<Class<?>> scanForGenericTypes(Object createdInstance) {
//    createdInstance.getClass().get
    List<Class<?>> resultList = new ArrayList<>();
    // scan for actual type arguments...
    //
    Type genericSuperclass = createdInstance.getClass().getGenericSuperclass();
    if (genericSuperclass instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
      Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
      for (Type t : actualTypeArguments) {
        resultList.add((Class<?>) t);
      }
    }
    return resultList;
  }

}
