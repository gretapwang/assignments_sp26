# CSC 210 Data Structures
## Assignment 4 Checklist

Listed below are various aspects of the assignment.  When you turn in
your work, please indicate the status of each item

- YES: indicates that the item is fully complete
- NO: indicates that the item is not attempted
- PART: indicates that the item is attempted but not fully complete

Note: this checklist covers only the coding part of the assignment (80%).
You should also make sure that your logic and ideas are clearly expressed to fulfill the prototyping requirement (20%).


## Grade-ability Check
Please confirm the following minimum criteria are met:

YES Program compiles without errors 

YES All required files included with submission (including basic readme info and completed checklist file) 

YES README.md contains answers to any questions and your reflection on the assignment 

**Assignments that do not meet the above criteria cannot be graded**


## Coding Points:

YES 1 pts: Code implements QueueADT<T> and StackADT<T> exactly

### Queue Implementation

YES 1 pts: enqueue(x) increases size by 1

YES 1 pts: dequeue() returns elements in first-in-first-out order

YES 1 pts: peek() returns next element without removing it

YES 1 pts: isEmpty() and size() return correct values

YES 1 pts: dequeue() on empty throws required IllegalArgumentException

YES 2 pts: Uses appropriate backing operations (no unnecessary O(n) behavior)

### Stack Implementation

YES 1 pts: push(x) increases size by 1

YES 1 pts: pop() returns elements in last-in-first-out order

YES 1 pts: peek() returns next element without removing it

YES 1 pts: isEmpty() and size() return correct values

YES 1 pts: pop() on empty throws IllegalArgumentException

YES 2 pts: Uses appropriate backing operations (no unnecessary O(n) behavior)

### PostFix

YES 1 pts: Pushes numeric tokens onto the stack

YES 2 pts: Processes operators by popping & combining numbers

YES 2 pts: Produces correct answer to any valid postfix expression

YES 1 pts: Throws an exception with an informative error message in case of malformed expressions


## Code Hygiene (4 pts):

YES 1 pt: No copy/paste near-duplicate code blocks for the same behavior (reusing your code is better for everyone!)

YES 1 pt: Common logic is factored into helpers 

YES 1 pt: Methods are short enough to read (no 100-line monster methods unless justified)

YES 1 pt: Names communicate intent (especially for helper methods)

### Kudos:
YES 0 pt: Evaluation of InFix expressions is attempted with the Shunting Yard Algorithm (code compiles and shows meaningful progress)

## General Items (6 pts):

YES 1 pt: Student-written code compiles without warnings that indicate correctness problems

YES 2 pts: Student-provided code runs and executes without unexpected crashing

YES 2 pt: Javadoc builds without errors/warnings

YES 1 pt: Indentation and other style norms are followed
