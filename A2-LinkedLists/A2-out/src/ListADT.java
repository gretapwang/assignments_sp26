/**
 * Interface to describe a list, which stores objects in an ordered sequence. The order cannot change on its own.
 * To initialize a list, we need to specify the type of its elements and an initial capacity.
 * Lists are created empty. We can then add any number of elements.
 */
interface ListADT<T> {
    
    /**
     * Getter for size.
     * 
     * @return The number of elements in the list
     */
    public int size();

    /**
     * Determines whether the list is empty.
     * 
     * @return True if there are no elements, false otherwise
     */
    public boolean isEmpty();

    /**
     * Returns the element at the specified index.
     * 
     * @param index The index to access
     * @return The element at the index
     * @throws IndexOutOfBoundsException For index less than 0 or greater than/equal to size
     */
    public T get(int index);

    /**
     * Sets the value at the specified index to the given object. Returns the previous value.
     * 
     * @param index The index to update
     * @param value The new value to set
     * @return The index's previous value
     * @throws IndexOutOfBoundsException For index less than 0 or greater than/equal to size
     */
    public T set(int index, T value);

    /**
     * Inserts the given object at the specified index. Shifts all subsequent elements to the right.
     * 
     * @param index The index to insert at
     * @param value The object to insert 
     * @throws IndexOutOfBoundsException For index less than 0 or greater than size
     */
    public void add(int index, T value);

    /**
     * Removes and returns the element at the specified index. Shifts all subsequent elements to the left.
     * 
     * @param index The index of the element to remove
     * @return The removed element
     * @throws IndexOutOfBoundsException For index less than 0 or greater than/equal to size
     */
    public T remove(int index);
}
