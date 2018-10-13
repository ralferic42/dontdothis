package com.github.ralferic42.dontdothis.reifiedcollections.typeserased;

import org.junit.Assert;
import org.junit.Test;

/**
 * test-class for {@link TypesErasedMap}
 */
public class TypesErasedMapTest {

  @Test
  public void testPutAndGet() {
    TypesErasedMap cut = new TypesErasedMap();
    TypesErasedMapKey<Integer> firstIntegerKey = new TypesErasedMapKey<Integer>() {};
    TypesErasedMapKey<Integer> secondIntegerKey = new TypesErasedMapKey<Integer>() {};
    TypesErasedMapKey<String> anotherKey = new TypesErasedMapKey<String>() {};

    Assert.assertNull( "previous content for 'firstIntegerKey' should be null", cut.put(firstIntegerKey, 1) );
    Assert.assertNull( "previous content for 'anotherKey' should be null", cut.put(anotherKey, "stringValue") );
    Assert.assertEquals(Integer.valueOf(1), cut.get(firstIntegerKey));
    Assert.assertEquals("stringValue", cut.get(anotherKey));

    Assert.assertNull( "previous content for 'secondIntegerKey' should be null", cut.put(secondIntegerKey, 2) );
    Assert.assertEquals(Integer.valueOf(2), cut.get(secondIntegerKey));

  }

}
