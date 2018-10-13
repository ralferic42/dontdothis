package com.github.ralferic42.dontdothis.reifiedcollections;

import org.junit.Assert;
import org.junit.Test;

/**
 * test-class for {@link TypedMap}
 */
public class TypedMapTest {

  @Test
  public void testPutAndGet() {
    TypedMap cut = new TypedMap();
    TypedMapKey<Integer> firstIntegerKey = new TypedMapKey<Integer>() {};
    TypedMapKey<Integer> secondIntegerKey = new TypedMapKey<Integer>() {};
    TypedMapKey<String> anotherKey = new TypedMapKey<String>() {};

    Assert.assertNull( "previous content for 'firstIntegerKey' should be null", cut.put(firstIntegerKey, 1) );
    Assert.assertNull( "previous content for 'anotherKey' should be null", cut.put(anotherKey, "stringValue") );
    Assert.assertEquals(Integer.valueOf(1), cut.get(firstIntegerKey));
    Assert.assertEquals("stringValue", cut.get(anotherKey));

    Assert.assertNull( "previous content for 'secondIntegerKey' should be null", cut.put(secondIntegerKey, 2) );
    Assert.assertEquals(Integer.valueOf(2), cut.get(secondIntegerKey));

  }

}
