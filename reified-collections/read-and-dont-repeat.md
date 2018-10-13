# Don't do this: reified collections
## How does it work ?
When you are using generic type-parameters, Java does not store that information for you. Instead, it relies on compile-time checking, the compiler will insert class-casts as appropriate. This behaviour is known to java programmers as 'type erasure'.

Type erasure does not mean that generic type parameters are inacessible. They are around and waiting to be collected during construction of a new class. A simple strategy will create this situation:

1. Use an abstract base class without any abstract methods.
2. Create a new instances like this: ... = new AbstractBaseClass() {};

Using this strategy, every new instance will be backed by a new anonymous inner class and will have access to its own set of generic type parameters.

The first software i know of using this trick is the guice dependency injection framework.

## Lets see it in action, Part 1: reified ArrayList

Our first example is a reified version of java.util.ArrayList, intended to be as close to a drop-in replacement as possible. Targetting it as a drop-in replacement is cause for quite a few problems, but they are not inherient to reifying a collection.

As mentioned above, every new instance will be backed by its own anonymous inner class. All constructors use GenericTypeInfo to analyze and store generic type parameters. Methods that modify the content have been 'upgraded' and execute a check. 

If you take a look at the code of GenericTypeInfo, you might find something amiss: there is no recursion in the analyzing process. I have left this out by intention, the code was meant to make the process easy to understand.

Another restriction is even more prominent: while GenericTypeInfo can generate a list of several (raw) types, ReifiedArrayList limits the number to one. The reason for this is the type information available for new values to store in the list. Each value will have exactly one class to check the type against. We have created this situation for our ReifiedArrayList and enabled each instance to have its own class with its own set of generic type parameters. If you want to store more complex types, you need to do something similar for the values. Storing complex types inside a reified list would require an explicit class for every single combination. 



## Lets see it in action, Part 2: Type encoded in the Map-Key

## Drawbacks and Problems

## Conclusion

 

​    