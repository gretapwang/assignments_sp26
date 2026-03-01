# A4 DIY Calculator

Your readme should include the following information. Each student needs to submit their own reflection, even when pair programming.  Please write N/A if an item does not apply.

## Basic Information

Your Name: Greta Wang

Other collaborators you worked with, including TAs (and feel free to give a shoutout to anyone who was particularly helpful): None

Any references used besides JavaDoc and course materials: None

## Questions for You

Which backing structure did you choose for your Stack/Queue (SLL or DynamicArray), and why? 

I chose SLL since it eliminates the need for resizing and shifting/wrapping elements. With an implementation that tracks the tail, SLL provides fast operations on both head and tail (minus removing from the tail, but we only need to be able to remove at one end).

What is the runtime cost of each primary operation (push/pop/peek for Stack; enqueue/dequeue/peek for Queue) with your choice?

All primary operations are constant time O(1). This is because my implementations are backed by an SLL where both the head and tail are stored, so both ends can be accessed/modified without traversing the list.

## Reflection

Reflection on your experience with this assignment:

I had fun with this assignment! It was interesting to see that although stacks and queues are new concepts, they were quick to implement since they reuse operations we already wrote for SLL. The infix calculator was a fun puzzle. I believe my code for it is working, but there are so many ways to form an infix expression that it's hard to make sure I've checked all the edge cases. This assignment solidified my understanding of the difference between stacks and queues, as well as their respective usefulness for various applications.

