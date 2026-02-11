# Assignment 1 Checklist | CSC 210

Listed below are various aspects of the assignment.  When you turn in
your work, please indicate the status of each item

- YES: indicates that the item is fully complete
- NO: indicates that the item is not attempted
- PART: indicates that the item is attempted but not fully complete

## Grade-ability Check
Please confirm the following minimum criteria are met:

YES Program compiles without errors 

YES All required files included with submission (including basic readme info and completed checklist file) 

YES README.md contains answers to any questions and your reflection on the assignment 

**Assignments that do not meet the above criteria cannot be graded**

## Coding Points (13 pts):

YES 1 pt: ListADT (from A0) included and includes all specified methods

YES 1 pt: DynamicArray method call signatures correctly implement ListADT

YES 1 pt: Uses a backing array (T[] data) + a size field (int size)

YES 1 pt: Maintains invariant: 0 ≤ size ≤ data.length

YES 1 pt: Logical index i maps to backing array index i for 0 ≤ i < size and items beyond size are not incorporated into operations

YES 1 pt: get(i) returns element at logical index i 

YES 1 pt: set(i,x) updates element at logical index i

YES 1 pt: add(i,x) inserts at logical index i (shifts right)

YES 1 pt: remove(i) removes logical index i (shifts left)

YES 1 pt: Bounds errors throw an appropriate unchecked exception (e.g., IndexOutOfBoundsException)

YES 1 pt: No checked exceptions used for normal ADT misuse

YES 1 pt: Adds that would make size > data.length trigger a resize

YES 1 pt: Resize correctly allocates a new array and copies existing elements in the correct order

## Code Hygiene (4 pts):

YES 1 pt: No copy/paste near-duplicate code blocks for the same behavior (reusing your code is better for everyone!)

YES 1 pt: Common logic is factored into helpers (e.g., checkIndex, resizeIfNeeded, shiftLeft/Right)

YES 1 pt: Methods are short enough to read (no 100-line monster methods unless justified)

YES 1 pt: Names communicate intent (especially for helper methods)


## General Items (6 pts):

YES 1 pt: Student-written code compiles without warnings that indicate correctness problems

YES 2 pts: Student-provided code runs and executes without unexpected crashing

YES 2 pt: Javadoc builds without errors/warnings

YES 1 pt: Indentation and other style norms are followed
