# A8 DIY SpellChecker

Each student should complete this README individually, even when pair programming.

## Basic Information

Your name: Greta Wang

Programming partner name, if any: none

Other collaborators, including TAs: none

If anyone was particularly helpful, please give them a shout-out here:

## References

Any references or resources used besides JavaDoc and course materials: none

If you used generative AI, how did you use it? What role did it play in your learning?

n/a

## Assignment Reflection

Please reflect on your experience with this assignment. Include:

- the benchmark output `Timer.java` reported when benchmarking the `ArrayList`-backed dictionary against the `HashSet`-backed dictionary
- what you observed in that benchmark
- which data structures you chose for the dictionary and near-miss suggestions, and why
- any other important design choices you made
- what was most challenging or most interesting about the assignment?

I reduced the number of repetitions in Timer.java to 10 and ran it. Here is the output:

contains() benchmark using 99168 words
ListDictionary: 63041452666 ns
HashSetDictionary: 56609958 ns

The HashSet implementation ran the contains() method faster than the ArrayList. This is what I expected to see since ArrayList has to check every element for a match, while HashSet only checks the relevant bucket based on hashcode. 

For my dictionary, I used HashSet because of the faster contains() operation. Efficient dictionary lookup is important for a spellchecker since we often need to check whether a particular word is valid. I also used HashSet for the near-miss suggestions. While we don't need to directly call contains() on the suggestions, we do need to add elements, and the add() method has to check whether the element is already present before adding it. This means HashSet helps with the efficiency of adding.

For the spellchecker standard input mode, I made the design choice of first storing all the words from the file in a set, and then processing them similarly to the command line arguments. I thought this would be most efficient since it eliminates duplicates early on and avoids doing any of the spellchecking process multiple times for duplicates. 

The most challenging part of the assignment was generating all possible edits of a word. It seemed very complicated at first, but eventually I realized that they all boil down to looping through the indices of the string and the letters of the alphabet.