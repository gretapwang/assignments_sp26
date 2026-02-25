# A3 Iteration and Copy-Style Slicing

## Basic Information

Your name: Greta Wang

Other students you worked with, including TAs: Tanya

If anyone was particularly helpful, please give them a shout-out here: Tanya!


## References

Any references or resources used besides JavaDoc and course materials: None

If you used generative AI, how did you use it? What role did it play in your learning?

N/A


## Questions to Answer

1. Which got slower faster as `N` increased: `get(mid)` or `add(0, x)` on `SLL`? Why?

get(mid) got slower faster than add(0, x) for SLL because accessing an intermediate index requires traversing each node up to the index, so a long list means more nodes to traverse. Meanwhile, to add an element at the start of the list, we only need to change the head, which doesn't require counting through the list.

2. Compare `splitCopy` vs `splitTransfer` for `DynamicArray` and `SLL`: what dominates the runtime in each?

For DynamicArray, splitTransfer takes longer than splitCopy. This makes sense since in addition to allocating a new array for the tail and copying over elements, splitTransfer also needs to remove each of those elements from the original. For SLL, splitTransfer also takes longer than splitCopy. This doesn't seem like it should be the case, since splitCopy has to traverse each node of the tail and create a new node, while splitTransfer just moves a few references. The TAs and I couldn't figure out why this happens. I'm wondering if it might have to do with the benchmark itself - it looks like for splitTransfer, a new list is created in each repetition, while splitCopy uses the same one each time.


## Reflection

Please provide a brief reflection about your experience with this assignment. What was easiest? What was hardest? How did your understanding of iteration and cost models evolve?

The easiest part of this assignment was implementing the iterator classes. Using iterators made the other methods easier to implement as well. It was hard to make my splitCopy and splitTransfer methods run quickly. I went to TA hours and received help from Tanya to make my code somewhat more efficient, but Timer.java still runs very slowly. In this assignment, I learned that an iterator class makes it easier to create complicated operations by simplifying the work of iterating through the list. I also gained a better understanding of the costs of different operations with DynamicArray vs LinkedList. As far as splitting, DynamicArray has the advantage of quickly accessing the index to split from, but LinkedList is efficient for transfer style since you can move nodes without making a deep copy.