# A2 Linked Lists

## Basic Information

Your name: Greta Wang

Other students you worked with, including TAs:

If anyone was particularly helpful, please give them a shout-out here: 


## References

Any references or resources used besides JavaDoc and course materials: None

If you used generative AI, how did you use it? What role did it play in your learning?

N/A


## Questions to Answer

What did you observe when comparing the benchmark results for index-based operations vs. node-based operations? Why do those results make sense for a linked list?

The node-based operations were much faster than the index-based ones. This makes sense because to access an item in a linked list by index, we have to count each node from the head up to the item. This process makes the operation much slower than accessing directly by node.


## Reflection 

Please provide a brief reflection about your experience with this assignment. What was easiest? What was hardest? How did your understanding of linked lists evolve?

The adding and removing methods were pretty easy to implement, since I only needed to modify the nodes adjacent to the change, and the rest of the list shifted automatically. This was much simpler than the array version, where I had to use for loops to shift all the elements. One difficult thing was writing methods that work on any part of the list, and on empty lists or lists with one element. The head had to be treated differently than other nodes, since it was stored as an attribute that needed updating. Despite that difficulty, this assignment helped me understand the advantages of linked lists, particularly the ease of adding and removing, and why we might prefer them over arrays when we want to modify a list efficiently.