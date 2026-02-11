/**
 * Stores objects in an ordered sequence. Elements can be accessed by index, added, and removed.
 *
 * @param <T> The type of element stored in the list. Can be any Object
 * @author Greta Wang
 */
public class DynamicArray<T> implements ListADT<T> {

    private T[] data; // Array used to store the elements
    private int size; // Number of elements

    /**
     * Creates a new generic array of the given capacity.
     * <p>
     * Java does not allow direct creation of generic arrays. This helper method
     * safely encapsulates the required cast and suppresses the expected unchecked
     * cast warning.
     *
     * @param capacity the desired length of the array
     * @return a new array of type T[] with the given capacity
     */
    @SuppressWarnings("unchecked")
    private T[] makeArray(int capacity) {
        return (T[]) new Object[capacity];
    }

    /**
     * Constructor creates an empty DynamicArray with the specified initial capacity.
     * 
     * @param capacity Initial capacity
     * @throws RuntimeException If given a negative capacity
     */
    public DynamicArray(int capacity) {
        if (capacity < 0) {
            throw new RuntimeException("Cannot create array with negative capacity");
        }
        this.data = makeArray(capacity);
        this.size = 0;
    }

    /**
     * Copy constructor creates a deep copy of the given DynamicArray.
     * 
     * @param original The list to copy
     */
    public DynamicArray(DynamicArray<T> original) {
        this.data = makeArray(original.size());
        this.size = original.size();
        original.copyElements(this.data, 0, original.size(), 0);
        // sets all elements of original into backing array of copy
    }

    /**
     * Getter for size.
     * 
     * @return The number of elements in the list
     */
    public int size() {
        return this.size;
    }

    /**
     * Determines whether the list is empty.
     * 
     * @return True if there are no elements, false otherwise
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Returns the element at the specified index.
     * 
     * @param index The index to access
     * @return The element at the index
     * @throws IndexOutOfBoundsException For index < 0 or index >= size
     */
    public T get(int index) {
        this.checkIndex(index);
        return this.data[index];
    }

    /**
     * Sets the value at the specified index to the given object. Returns the previous value.
     * 
     * @param index The index to update
     * @param value The new value to set
     * @return The index's previous value
     * @throws IndexOutOfBoundsException For index < 0 or index >= size
     */
    public T set(int index, T value) {
        this.checkIndex(index);
        T previousValue = this.data[index];
        this.data[index] = value;
        return previousValue;
    }

    /**
     * Inserts the given object at the specified index. Shifts all subsequent elements to the right.
     * 
     * @param index The index to insert at
     * @param value The object to insert 
     * @throws IndexOutOfBoundsException For index < 0 or index > size
     */
    public void add (int index, T value) {
        this.checkIndexInclusive(index);
        if (this.size < this.data.length) { // if sufficient capacity, shift elements over w/o replacing array
            for (int i = this.size() - 1; i >= index; i--) {
                this.data[i + 1] = this.data[i];
            }
            this.data[index] = value;
        } else { // replace array if needed, and copy elements over
            T[] temp = makeArray(this.data.length + 1);
            this.copyElements(temp, 0, index, 0);
            temp[index] = value;
            this.copyElements(temp, index, this.size(), 1);
            this.data = temp;
        }
        this.size += 1; // update size attribute
    }

    /**
     * Overloaded add() which adds the given object at the end of the list.
     * 
     * @param value The object to add
     */
    public void add(T value) {
        this.add(this.size(), value);
    }

    /**
     * Removes and returns the element at the specified index. Shifts all subsequent elements to the left.
     * 
     * @param index The index of the element to remove
     * @return The removed element
     * @throws IndexOutOfBoundsException For index < 0 or index >= size
     */
    public T remove(int index) {
        this.checkIndex(index);
        T removedElement = this.data[index];
        this.copyElements(this.data, index + 1, this.size(), -1); // shift elements to the left
        this.data[this.size() - 1] = null;
        this.size -= 1; // update size attribute
        return removedElement;
    }

    /**
     * Returns a cleanly formatted String listing the elements in order.
     * 
     * @return String containing the elements
     */
    public String toString() {
        String formattedList;
        if (this.isEmpty()) {
            formattedList = "[]";
        } else {
            formattedList = "[" + this.data[0];
            for (int i = 1; i < this.size(); i++) {
                formattedList += (", " + this.data[i]);
            }
            formattedList += "]";
        }
        return formattedList;
    }

    /**
     * Returns a DynamicArray formed by concatenating the given DynamicArray onto the current one. 
     * 
     * @param array2 The list to add on the end
     * @return The concatenated list
     */
    public DynamicArray<T> append(DynamicArray<T> array2) {
        return this.addAll(this.size(), array2);
    }

    /**
     * Returns a DynamicArray formed by inserting the given DynamicArray into the current one 
     * at the specified index.
     * 
     * @param index The index to insert at
     * @param array2 The list to insert
     * @return The list with new elements inserted
     * @throws IndexOutOfBoundsException For index < 0 or index > size
     */
    public DynamicArray<T> addAll(int index, DynamicArray<T> array2) {
        this.checkIndexInclusive(index);
        // copy the current DynamicArray, then add elements from the other
        DynamicArray<T> newArray = new DynamicArray<T>(this);
        for (int i = 0; i < array2.size(); i++) {
            newArray.add(index + i, array2.get(i));
        }
        return newArray;
    }

    /**
     * Returns a DynamicArray containing the elements of the current DynamicArray 
     * at the specified index and later.
     * 
     * @param index The index to split at
     * @return The later section of the list
     * @throws IndexOutOfBoundsException For index < 0 or index > size
     */
    public DynamicArray<T> splitSuffix(int index) {
        // delete() throws the IndexOutOfBoundsException
        return this.delete(0, index);
    }

    /**
     * Returns a DynamicArray containing the elements of the current DynamicArray 
     * up to the specified index, exclusive.
     * 
     * @param index The index to split at
     * @return The first section of the list
     * @throws IndexOutOfBoundsException For index < 0 or index > size
     */
    public DynamicArray<T> splitPrefix(int index) {
        // delete() throws the IndexOutOfBoundsException
        return this.delete(index, this.size());
    }

    /**
     * Returns a DynamicArray containing the elements of the current DynamicArray, 
     * except for those in the specified range, inclusive to exclusive.
     * 
     * @param fromIndex The starting index for the deleted range, included
     * @param toIndex The ending index for the deleted range, excluded
     * @return The new list with items removed
     * @throws IndexOutOfBoundsException If indices do not satisfy 0 <= fromIndex <= toIndex <= size
     */
    public DynamicArray<T> delete(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > toIndex || toIndex > this.size()) {
            throw new IndexOutOfBoundsException();
        }
        DynamicArray<T> newArray = new DynamicArray<T>(this); // copy the DynamicArray, then remove elements
        for (int i = 0; i < toIndex - fromIndex; i++) {
            newArray.remove(fromIndex);
        }
        return newArray;
    }

    /**
     * Returns a DynamicArray containing the elements of the current DynamicArray within the specified range, 
     * inclusive to exclusive.
     * 
     * @param fromIndex The starting index for the extracted range, included
     * @param toIndex The ending index for the extracted range, excluded
     * @return The extracted list
     * @throws IndexOutOfBoundsException If indices do not satisfy 0 <= fromIndex <= toIndex <= size
     */
    public DynamicArray<T> extract(int fromIndex, int toIndex) {
        // delete() throws the IndexOutOfBoundsException
        return this.delete(0, fromIndex).delete(toIndex - fromIndex, this.size() - fromIndex);
    }

    /**
     * Helper method to check that an index is valid, i.e. 0 <= index < size.
     * 
     * @param index The index to check
     * @throws IndexOutOfBoundsException For index < 0 or index >= size
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Helper method to check that an index is valid, where index = size is considered valid.
     * 
     * @param index The index to check
     * @throws IndexOutOfBoundsException For index < 0 or index > size
     */
    private void checkIndexInclusive(int index) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Helper method to take the elements in a specified range (inclusive to exclusive) and set them in a given 
     * native array, shifting the position of the elements in the array as specified.
     * 
     * @param array The array to modify
     * @param fromIndex The starting index in the DynamicArray for the copied range, included
     * @param toIndex The ending index in the DynamicArray for the copied range, excluded
     * @param shift The change in each element's index between the DynamicArray and the passed array
     * @throws IndexOutOfBoundsException If the original index range is invalid for the DynamicArray, 
     * or the shifted range is invalid for the array
     */
    private void copyElements(T[] array, int fromIndex, int toIndex, int shift) {
        if (fromIndex < 0 || fromIndex > toIndex || toIndex > this.size() 
            || fromIndex + shift < 0 || toIndex + shift > array.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = fromIndex; i < toIndex; i++) {
            array[i + shift] = this.get(i);
        }
    }
}
