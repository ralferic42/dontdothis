# Don't do this: reified collections
## How does it work ?
When you are using generic type-parameters, Java does not store that information for you. Instead, 
it relies on compile-time checking, trhe compiler will insert class-casts as appropriate. 
This behaviour is known to java programmers as 'type erasure'.

Type erasure does not mean that generic type parameters are inacessible. They are around and waiting to be 
collected during construction of an anonymous inner class. If you create an abstract base class
without any abstract methods, each new instance can be created by adding {} and will have access
to its generic type parameters.

The first software i know of using this trick is the guice dependency injection framework.

## Lets see it in action, Part 1: reified ArrayList

## Lets see it in action, Part 2: reified ArrayList

## Drawbacks and Problems

## Conclusion

 
  
    