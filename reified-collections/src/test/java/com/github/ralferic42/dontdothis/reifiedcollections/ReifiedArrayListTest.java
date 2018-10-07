package com.github.ralferic42.dontdothis.reifiedcollections;

import org.junit.Assert;
import org.junit.Test;

/**
 * test-class for {@link ReifiedArrayList}
 */
public class ReifiedArrayListTest {
  @Test
  public void testGenericTypeDetection() {
    ReifiedArrayList<String> cut = new ReifiedArrayList<String>() {};
    Assert.assertEquals("generic type argument not correctly detected", String.class, cut.getClassToCheckAgainst());
  }
}
