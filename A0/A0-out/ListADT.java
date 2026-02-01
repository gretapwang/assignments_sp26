/**
 * Interface to describe a list, which stores objects of a certain type in a sequence. 
 * To initialize a list, we need to specify the type of its elements. 
 * Lists are created empty, with capacity zero. As elements are added/removed, 
 * they shift positions and the list is resized so that there are never empty spots.
 */
interface ListADT<T> {
    
    /**
     * Returns the number of elements in the list
     * @return The number of elements
     */
    public int size();

    /**
     * Determines whether the list is empty
     * @return True if there are no elements in the list, false otherwise
     */
    public boolean isEmpty();

    /**
     * Returns the element at the given position in the list
     * Throws IndexOutOfBoundsException if the index is less than zero,
     * or greater than or equal to the list's size
     * @param index The position to access
     * @return The element at the given position
     */
    public T get(int index);

    /**
     * Adds the given object to the list at the specified position
     * Throws IndexOutOfBoundsException if the index is less than zero,
     * or greater than the list's size
     * @param element The object to add
     * @param index The position to insert the object
     */
    public void insert(T element, int index);

    /**
     * Removes the element at the given position in the list
     * Throws IndexOutOfBoundsException if the index is less than zero,
     * or greater than or equal to the list's size
     * @param index The position to remove an element
     */
    public void remove(int index);
}
