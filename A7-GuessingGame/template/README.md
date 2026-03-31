# A7 Guessing Game

Each student should complete this README individually, even when pair programming.

## Basic Information

Your name: Greta Wang

Programming partner name, if any: none

Other collaborators, including TAs: none

If anyone was particularly helpful, please give them a shout-out here:

## References

Any references or resources used besides JavaDoc and course materials: none

If you used generative AI, how did you use it? What role did it play in your learning?

N/A

## Questions to Answer

How does your `DecisionTree` class differ from the base `BinaryTree<String>` class?

My DecisionTree class supports additional behavior based on the idea that each child represents a yes or no answer to a question contained in the parent. DecisionTree can access the node resulting from a series of yes/no choices, and can read and write the information about this structure to a file.

How does your program decide whether to move left or right while playing the guessing game?

My program asks the user to answer the question at the current node, converts their input to a boolean value, and moves left if the boolean is true (i.e. answer is yes) and right if false.

How does your program update and save what it learned after an incorrect guess?

My program asks the user for their animal, a question to distinguish between their animal and the program's guess, and whether their animal is the 'yes' answer to the question. Then the node at which the incorrect guess was located becomes the new question, and the correct and guessed animals become its children, with the 'yes' animal being the left child. When the user ends the game, the program saves the new information by writing the updated tree to the file.

## Reflection

Please provide a brief reflection on your experience with this assignment. What was most challenging? What was most interesting? What did you learn about representing decisions with trees?

This assignment was very interesting to me. The hardest parts were navigating inheritance and reading/writing to files, as I was rusty on both of those concepts. It was interesting to see the familiar game of 20 questions represented as a data structure. I hadn't thought before about how one would describe the game's progression, but now I see that it naturally lends itself to a tree representation. I learned that trees are a simple way of representing data that result from a series of decisions, and that the tree allows us to add new decisions easily. I'm still curious what applications decision trees are used for, aside from games like this.
