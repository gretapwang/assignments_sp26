# A6 Card Sorting
CSC 210: Data Structures

## General Information

Your readme should include the following information.

Your name: Greta Wang

Other collaborators: None

Was anyone particularly helpful? Give them a shout-out here:

## References

References used (besides JavaDoc and course materials): None

If you used AI at all for this assignment: How did you use it? What did this experience teach you?

N/A

## Benchmarking

| | LinearSearch | SelectionSort | InsertionSort |
| --- | --- | --- | --- |
|10000 cards | 0.05s | 0.23s | 0.2s |
|20000 cards | 0.05s | 0.7s | 0.63s |
|40000 cards | 0.05s | 2.67s | 2.54s |
|80000 cards | 0.05s | 11.85s | 10.26s |
|160000 cards | 0.07s | 52.9s | 42.26s |
|320000 cards | 0.07s | 220.38s | 147.81s |

I benchmarked LinearSearch, SelectionSort, and InsertionSort using the 'user' measurement. Linear search runs faster than I expected, with the runtime essentially constant rather than linear. I am not sure why this is. As the number of cards increases, it becomes clear that insertion is faster than selection. This matches my expectations since, as we discussed in class, selection sort is always O(n^2), while insertion sort is O(n^2) only at its worst. This results from the fact that for selection, each card transfer requires checking all unsorted cards to find the smallest one. For insertion, however, we don't necessarily need to check all sorted cards during each transfer; we stop checking once we reach a card greater than the one we're placing.

## Reflection Questions

What did you notice about the differences in runtime across algorithms as you changed the number of cards you were sorting? If you had to split them into "slower" algorithms vs "faster" algorithms, which would you put in each category?

As I ran the timer with more cards, the runtimes for linear search and merge sort increased very slowly, while for selection sort and insertion sort they increased quickly at an approximately quadratic rate. Within those general trends, linear search was somewhat faster than merge, and insertion was somewhat faster than selection. I would say linear search and merge sort are the faster algorithms, and selection sort and insertion sort are slower.

After implementing these algorithms with linked-list-style operations, which methods would have benefited most from using `ArrayList` instead of `LinkedList`, and why? Which methods do you think were a better fit for linked lists?

Linear search is fine with either type of list, since it just requires iterating through. Selection sort would benefit from ArrayList since while finding the smallest unsorted element, we could store the index of the smallest one so far and easily access it later. Shifting is not an issue in this case because we only sort elements onto the end of the sorted section, which can be done with one swap. Meanwhile, insertion sort involves inserting elements at various points in the sorted section, which would require shifting with ArrayList. Thus, insertion sort is better suited to LinkedList. Merge sort is also best with LinkedList, since merging sections of an ArrayList means making temporary storage and copying over elements.

Why didn't we implement binary search for this assignment?

Binary search would be inefficient with LinkedList because in order to cut the list in half, we would have to traverse half the list to find the middle node.

## Reflection

What was your overall experience with this assignment? What was most challenging? What was most interesting?

This assignment was overall not too difficult. It was hard at first to conceptualize what each sorting algorithm does and why they all work, but thinking back to the demonstrations in class was helpful. I also understood better once I implemented the algorithms and could see the sorting visuals. It was interesting to see that merge sort is way more efficient than selection and insertion, especially because in the abstract sense it seems like a more complicated algorithm. This assignment also gave me practice with iterators and helped me see how useful they are for avoiding indices with LinkedList.
