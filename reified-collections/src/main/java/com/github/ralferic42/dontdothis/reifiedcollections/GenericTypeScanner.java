package com.github.ralferic42.dontdothis.reifiedcollections;

import java.util.Arrays;
import java.util.List;

/**
 * Scanner for generic type arguments used during construction of an anonymous class
 */
public class GenericTypeScanner {
  public List<Class<?>> scanForGenericTypes(Object createdInstance) {
    // TODO: scan for actual type arguments
    return Arrays.asList(null);
  }
}
