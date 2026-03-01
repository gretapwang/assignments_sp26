/**
 * Stack class storing objects in LIFO order.
 * 
 * @param <T> Type of object stored
 * @author Greta Wang
 * @version Spring 2026
 */
public class Stack<T> implements StackADT<T> {
    
    private SLL<T> data; // linked list to store items

    /**
     * Constructor creates an empty stack.
     */
    public Stack() {
        this.data = new SLL<T>();
    }

    /**
     * Adds an item to the top of the stack.
     * 
     * @param value Item to add
     */
    public void push(T value) {
        this.data.addFirst(value);
    }

    /**
     * Removes and returns the top item from the stack.
     * 
     * @return Top item
     * @throws IllegalArgumentException If the stack is empty
     */
    public T pop() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException("Malformed expression");
        }
        return this.data.removeFirst();
    }

    /**
     * Returns the top item from the stack without removing it.
     * Returns null if empty.
     * 
     * @return Top item
     */
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.data.getHead().getData();
    }

    /**
     * Returns true if there are no elements to pop.
     * 
     * @return True if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    /**
     * Returns the number of elements in the stack.
     * 
     * @return Number of elements
     */
    public int size() {
        return this.data.size();
    }
}
