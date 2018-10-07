package com.github.ralferic42.dontdothis.reifiedcollections;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * test-class for {@link ReifiedArrayList}
 */
public class ReifiedArrayListTest {
  @Test
  public void testGenericTypeDetection() {
    ReifiedArrayList<String> cut = new ReifiedArrayList<String>() {};
    Assert.assertEquals("generic type argument not correctly detected", String.class, cut.getClassToCheckAgainst());
    ReifiedArrayList<List<String>> cut2 = new ReifiedArrayList<List<String>>() {};
    Assert.assertEquals("generic type argument not correctly detected", List.class, cut2.getClassToCheckAgainst());
  }
}
