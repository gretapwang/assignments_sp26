# A0 List ADT
CSC 210: Data Structures

## General Information
Your readme should include the following information. Each student needs to submit all of this information.

Your name: Greta Wang

Other students you worked with, including TAs: None

If anyone was particularly helpful, please give them a shoutout here!: 

## References

### References used (besides JavaDoc and course materials):

None

### If you used AI at all for this assignment: How did you use it? What did this experience teach you?

n/a

## Reflection Questions

### What was your overall experience with this assignment? What was hardest? What was easiest?

This assignment went ok overall. It was a little tricky to decide how a list should work, since we didn't have a specific plan of what it would be used for. Once I decided on the properties it should have, writing the interface was fairly easy, as I was familiar with interfaces from CSC120. Another difficult task was figuring out how to describe tests in the table - office hours were helpful for this.

### What was your hardest ListADT design decision, and why?

It was hard to decide how the indexing should work, since different setups would be useful depending on the situation. I considered making it possible for a list to have empty spaces between the elements. However, I decided against this and made it so the indices always go from 0 to (size - 1), since this better represents the physical examples of sequences we saw in class, where there were no empty spots.

### What assumptions did you make while designing ListADT?

I assumed it would be fine to have the same element included multiple times in a list, so the insert() method I outlined won't throw an exception if given an element that is already on the list. I also assumed that a list should only have elements of a certain type, and that we would know the type when creating the list and wouldn't need to change it later.
