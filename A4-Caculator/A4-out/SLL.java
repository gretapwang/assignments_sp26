import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Class to implement a singly linked list.
 *
 * @param <T> The object type stored in the list
 * @author Greta Wang
 * @version Spring 2026
 */
public class SLL<T> implements ListADT<T>, Iterable<T> {

    private NodeSL<T> head; // first node 
    private NodeSL<T> tail; // last node
    private int size; // number of items

    /**
     * Constructor creates an empty list.
     */
    public SLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
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
        return this.size == 0;
    }

    /**
     * Returns the element at the specified index.
     * 
     * @param index The index to access
     * @return The element at the index
     * @throws IllegalStateException If the list is empty
     * @throws IndexOutOfBoundsException For index less than 0 or greater than/equal to size
     */
    public T get(int index) {
        // getNode() throws exceptions as needed
        return this.getNode(index).getData();
    }

    /**
     * Sets the value at the specified index to the given object. Returns the previous value.
     * 
     * @param index The index to update
     * @param value The new value to set
     * @return The index's previous value
     * @throws IllegalStateException If the list is empty
     * @throws IndexOutOfBoundsException For index less than 0 or greater than/equal to size
     */
    public T set(int index, T value) {
        // getNode() throws exceptions as needed
        T old = this.getNode(index).getData();
        this.getNode(index).setData(value);
        return old;
    }

    /**
     * Inserts the given object at the specified index.
     * 
     * @param index The index to insert at
     * @param value The object to insert 
     * @throws IndexOutOfBoundsException For index less than 0 or greater than size
     */
    public void add(int index, T value) {
        if (index == 0) {
            this.addFirst(value);
        } else if (index == this.size()) {
            this.addLast(value);
        } else {
            this.checkIndexInclusive(index);
            NodeSL<T> newNode = new NodeSL<T>(value, null);
            NodeSL<T> nodeBefore = this.getNode(index - 1);
            newNode.setNext(nodeBefore.getNext());
            nodeBefore.setNext(newNode);
            this.size += 1;
        }
    }

    /**
     * Removes and returns the element at the specified index.
     * 
     * @param index The index of the element to remove
     * @return The removed element
     * @throws IllegalStateException If the list is empty
     * @throws IndexOutOfBoundsException For index less than 0 or greater than/equal to size
     */
    public T remove(int index) {
        if (index == 0) {
            return this.removeFirst();
        } else if (index == this.size() - 1) {
            return this.removeLast();
        } else {
            this.checkIndex(index);
            NodeSL<T> nodeBefore = this.getNode(index - 1);
            T value = nodeBefore.getNext().getData();
            nodeBefore.setNext(nodeBefore.getNext().getNext());
            this.size -= 1;
            return value;
        }
    }

    /**
     * Returns a clearly formatted String containing all elements in order.
     * 
     * @return String of elements
     */
    public String toString() {
        String formattedList = "[";
        for (NodeSL<T> item = this.head; item != null; item = item.getNext()) {
            if (item.getNext() == null) {
                formattedList += item.getData();
            } else {
                formattedList += item.getData() + ", ";
            }
        }
        formattedList += "]";
        return formattedList;
    }

    /**
     * Helper method returns the node at the specified index.
     * 
     * @param index The index to access
     * @return The node at the index
     * @throws IllegalStateException If the list is empty
     * @throws IndexOutOfBoundsException For index less than 0 or greater than/equal to size
     */
    private NodeSL<T> getNode(int index) {
        this.checkNonEmpty();
        this.checkIndex(index);
        if (index == this.size() - 1) {
            return this.tail; // for efficiency, use stored tail if getting tail
        }
        NodeSL<T> node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    /**
     * Helper method to check that an index is valid.
     * 
     * @param index The index to check
     * @throws IndexOutOfBoundsException For index less than 0 or greater than/equal to size
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Helper method to check that an index is valid, counting index = size as valid.
     * 
     * @param index The index to check
     * @throws IndexOutOfBoundsException For index less than 0 or greater than size
     */
    private void checkIndexInclusive(int index) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Helper method to check that the list is not empty.
     * 
     * @throws IllegalStateException If the list is empty
     */
    private void checkNonEmpty() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    /**
     * Accessor for head node.
     * 
     * @return The head node
     */
    public NodeSL<T> getHead() {
        return this.head;
    }

    /**
     * Accessor for tail node.
     * 
     * @return The tail node, null if empty list
     */
    public NodeSL<T> getTail() {
        return this.tail;
    }

    /**
     * Inserts the given item at the head of the list. 
     * 
     * @param v Item to insert 
     */
    public void addFirst(T v) {
        this.head = new NodeSL<T>(v, this.head);
        if (this.isEmpty()) {
            this.tail = this.head;
        }
        this.size += 1;
    }

    /**
     * Inserts the given item at the tail of the list.
     * 
     * @param v Item to insert 
     */
    public void addLast(T v) {
        if (this.isEmpty()) {
            this.addFirst(v);
        } else {
            NodeSL<T> newTail = new NodeSL<T>(v, null);
            this.tail.setNext(newTail);
            this.tail = newTail;
            this.size += 1;
        }
    }

    /**
     * Removes and returns the item from the head of the list.
     * 
     * @return Item removed
     * @throws IllegalStateException If the list is empty
     */
    public T removeFirst() {
        this.checkNonEmpty();
        T value = this.head.getData();
        this.head = this.head.getNext(); // update head to the following node
        if (this.size() == 1) {
            this.tail = null;
        }
        this.size -= 1;
        return value;
    }

    /**
     * Removes and returns the item from the tail of the list.
     * 
     * @return Item removed
     * @throws IllegalStateException If the list is empty
     */
    public T removeLast() {
        this.checkNonEmpty();
        if (this.size() == 1) { // if size = 1, removing first and last are the same
            return this.removeFirst();
        } else { // update tail
            T value = this.tail.getData();
            this.tail = this.getNode(this.size() - 2);
            this.tail.setNext(null);
            this.size -= 1;
            return value;
        }
    }

    /**
     * Inserts the given item after the specified node.
     * If here is null, insert at the head.
     * 
     * @param here Node to insert after
     * @param v Item to insert 
     */
    public void addAfter(NodeSL<T> here, T v) {
        if (here == null) { // add at head
            this.addFirst(v);
        } else if (here == this.tail) { // add at tail
            this.addLast(v);
        } else {
            NodeSL<T> newNode = new NodeSL<T>(v, here.getNext());
            here.setNext(newNode);
            this.size += 1;
        }
    }

    /**
     * Removes and returns the node after the given position.
     * If here is null, remove the head node.
     * 
     * @param here Node to remove after
     * @return Item removed
     * @throws IllegalStateException If the list is empty
     * @throws IllegalArgumentException If here is at the tail
     */
    public T removeAfter(NodeSL<T> here) {
        if (here == null) { // remove from head
            return this.removeFirst();
        } else if (here.getNext() == this.tail) { // remove from tail
            return this.removeLast();
        } else {
            this.checkNonEmpty();
            if (here == this.tail) { // throw exception if here is the tail
                throw new IllegalArgumentException();
            }
            T value = here.getNext().getData();
            here.setNext(here.getNext().getNext());
            this.size -= 1;
            return value;
        }
    }

    /**
     * Copy constructor creates a deep copy of the given list.
     * 
     * @param other The list to copy
     */
    public SLL(SLL<T> other) {
        this();
        for (NodeSL<T> item = other.getHead(); item != null; item = item.getNext()) {
            this.addLast(item.getData());
        }
    }

    /**
     * Returns an iterator positioned at the start of the list.
     * 
     * @return The new iterator
     */
    public Iterator<T> iterator() {
        return new SLLIterator();
    }

    /**
     * Returns an iterator positioned before the given index.
     * 
     * @param index The index to start before
     * @return The iterator
     * @throws IndexOutOfBoundsException For index less than 0 or greater than size
     */
    public Iterator<T> iterator(int index) {
        return new SLLIterator(index);
    }

    /**
     * Iterator class for an SLL. 
     * 
     * @author Greta Wang
     * @version Spring 2026
     */
    private class SLLIterator implements Iterator<T> {
        
        private NodeSL<T> pos; // Node which the iterator is adjacent to
        private boolean onLeft; // True if iterator is to the left of pos

        /**
         * Constructor creates an iterator positioned at the start of the list.
         */
        public SLLIterator() {
            this.pos = head;
            this.onLeft = true;
        }

        /**
         * Constructor creates an iterator positioned before the given index.
         * 
         * @param index The index to start before
         * @throws IndexOutOfBoundsException For index less than 0 or greater than size
         */
        public SLLIterator(int index) {
            this();
            checkIndexInclusive(index);
            for (int i = 0; i < index; i++) {
                this.next();
            }
        }

        /**
         * Returns true if there are any elements after the iterator's position.
         * 
         * @return True if there is a next element, false otherwise
         */
        public boolean hasNext() {
            if (this.onLeft && this.pos != null) {
                return true;
            } else if (this.onLeft) {
                return false;
            } else {
                return (this.pos.getNext() != null);
            }
        }

        /**
         * Returns the next element and advances the iterator.
         * 
         * @return The next element
         * @throws NoSuchElementException If there is no next element
         */
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            if (this.onLeft) {
                this.onLeft = false; // move to the right of pos
            } else {
                this.pos = this.pos.getNext();
            }
            return this.pos.getData();
        }
    }

    /**
     * Returns a deep copy of the tail of the list, starting at the specified index.
     * The current list is unchanged.
     * 
     * @param index The starting index for the tail
     * @return The copy of the tail
     * @throws IndexOutOfBoundsException For index less than 0 or greater than size
     */
    public SLL<T> splitCopy(int index) {
        SLL<T> tail = new SLL<T>();
        Iterator<T> iter = this.iterator(index); // iterator constructor throws exception if invalid index
        if (iter.hasNext()) {
            NodeSL<T> curr = new NodeSL<T>(iter.next(), null);
            tail.head = curr;
            NodeSL<T> newNode;
            while (iter.hasNext()) {
                newNode = new NodeSL<T>(iter.next(), null);
                curr.setNext(newNode);
                curr = newNode; // curr tracks last item added, to avoid traversal
            }
            tail.tail = curr;
        }
        tail.size = this.size() - index;
        return tail;
    }

    /**
     * Removes the tail of the list, starting at the specified index.
     * Returns the tail as a new list, using the same nodes previously in the current list.
     * 
     * @param index The starting index for the tail
     * @return The tail
     * @throws IndexOutOfBoundsException For index less than 0 or greater than size
     */
    public SLL<T> splitTransfer(int index) {
        this.checkIndexInclusive(index);
        SLL<T> tail = new SLL<T>();
        if (index == 0) {
            tail.head = this.head;
            tail.tail = this.tail;
            this.head = null;
            this.tail = null;
        } else if (index < this.size()) {
            NodeSL<T> beforeTail = this.getNode(index - 1);
            tail.head = beforeTail.getNext();
            tail.tail = this.tail;
            beforeTail.setNext(null);
            this.tail = beforeTail;
        }
        tail.size = this.size() - index;
        this.size = index;
        return tail;
    }
}
