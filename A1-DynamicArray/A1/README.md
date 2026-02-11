# A1 DynamicArray

## Basic Information

Your name: Greta Wang

Other students you worked with, including TAs: None

If anyone was particularly helpful, please give them a shout-out here: 


## References

Any references or resources used besides JavaDoc and course materials: None

If you used generative AI, how did you use it? What role did it play in your learning?

N/A


## Questions to Answer

What is the difference between size and capacity?

Size is the number of elements currently in the list, while capacity is the number that the backing array is able to store.

When do we resize and why?

We resize when we need to add elements, but don't have space in the array. We need to do this because arrays cannot change capacity in Java, so once the array is full, we have to replace it with a larger array in order to store more.

Which operations require shifting and why?

Adding and removing elements require shifting. For adding, the shift is needed in order to make space at the index of insertion without deleting any other elements. For removing, shifting updates the remaining elements' indices so they are still numbered consecutively from 0 to size - 1.

## Reflection 

Please provide a brief reflection about your experience with this assignment. What was easiest? What was hardest? How did your understanding of arrays and lists evolve?

I found it pretty easy to implement the actual code for this assignment, but trickier to make decisions such as which indices are valid in different situations, or which methods should reuse one another to make my code efficient. Testing efficiently was also hard - even with many tests for each method, there are some edge case combinations I'm missing. 
This assignment helped me understand the distinction between the conceptual part of a list and the data structure it's built on. I learned that although the structures we work with have limitations, we can write workarounds into our code.
