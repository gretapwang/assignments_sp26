/**
 * Queue class storing objects in FIFO order.
 * 
 * @param <T> Type of object stored
 * @author Greta Wang
 * @version Spring 2026
 */
public class Queue<T> implements QueueADT<T> {
    
    private SLL<T> data; // linked list to store items

    /**
     * Constructor creates an empty queue.
     */
    public Queue() {
        this.data = new SLL<T>();
    }

    /**
     * Adds an item to the tail of the queue.
     * 
     * @param value Item to add
     */
    public void enqueue(T value) {
        this.data.addLast(value);
    }

    /**
     * Removes and returns the head item of the queue.
     * 
     * @return Head item
     * @throws IllegalArgumentException If the queue is empty
     */
    public T dequeue() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException("Malformed expression");
        }
        return this.data.removeFirst();
    }

    /**
     * Returns the head item of the queue without removing it.
     * Returns null if empty.
     * 
     * @return Head item
     */
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.data.getHead().getData();
    }

    /**
     * Returns true if there are no elements to dequeue.
     * 
     * @return True if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    /**
     * Returns the number of elements in the queue.
     * 
     * @return Number of elements
     */
    public int size() {
        return this.data.size();
    }
}
