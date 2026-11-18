/*
 * Name: Rishit Rakapali
 * Email: rrakapali@ucsd.edu
 * TSN: 200440552
 * Sources Used: PA6 write-up, course materials
 *
 * This file implements a generic double-ended queue using a circular array.
 * Elements can be inserted, removed, and accessed at either end.
 */

/**
 * Implements a generic deque backed by a resizable circular array.
 * Tracks the first and last elements with front and rear indices.
 *
 * @param <E> the type of elements stored in the deque
 */
public class MyDeque<E> implements DequeInterface<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int EXPAND_CAPACITY_RATIO = 2;

    Object[] data;
    int size;
    int rear;
    int front;

    /**
     * Creates an empty deque with the specified initial capacity.
     *
     * @param initialCapacity the initial length of the backing array
     */
    public MyDeque(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }

        data = new Object[initialCapacity];
        size = 0;
        front = 0;
        rear = 0;
    }

    /**
     * Returns the number of stored elements, excluding unused array slots.
     *
     * @return the number of elements in the deque
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Doubles the capacity, or uses the default capacity if it was zero.
     * Copies elements into logical order starting at array index zero.
     */
    @Override
    public void expandCapacity() {
        int newCapacity;
        if (data.length == 0) {
            newCapacity = DEFAULT_CAPACITY;
        } else {
            newCapacity = data.length * EXPAND_CAPACITY_RATIO;
        }
        Object[] expandedData = new Object[newCapacity];

        // Preserve logical order even when the original array wraps around.
        for (int index = 0; index < size; index++) {
            expandedData[index] = data[(front + index) % data.length];
        }

        data = expandedData;
        front = 0;
        if (size == 0) {
            rear = 0;
        } else {
            rear = size - 1;
        }
    }

    /**
     * Inserts a non-null element before the current front of the deque.
     * Expands the backing array if all slots are occupied.
     *
     * @param element the element to insert at the front
     */
    @Override
    public void addFirst(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size == data.length) {
            expandCapacity();
        }

        if (size > 0) {
            front = (front - 1 + data.length) % data.length;
        }

        data[front] = element;
        size++;
    }

    /**
     * Inserts a non-null element after the current rear of the deque.
     * Expands the backing array if all slots are occupied.
     *
     * @param element the element to insert at the rear
     */
    @Override
    public void addLast(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size == data.length) {
            expandCapacity();
        }

        if (size > 0) {
            rear = (rear + 1) % data.length;
        }

        data[rear] = element;
        size++;
    }

    /**
     * Removes the first element without changing the rear index.
     * An empty deque returns null without changing its indices.
     *
     * @return the removed first element, or null if the deque is empty
     */
    @Override
    public E removeFirst() {
        if (size == 0) {
            return null;
        }

        E removedElement = (E) data[front];
        data[front] = null;

        if (size > 1) {
            front = (front + 1) % data.length;
        }

        size--;
        return removedElement;
    }

    /**
     * Removes the last element without changing the front index.
     * An empty deque returns null without changing its indices.
     *
     * @return the removed last element, or null if the deque is empty
     */
    @Override
    public E removeLast() {
        if (size == 0) {
            return null;
        }

        E removedElement = (E) data[rear];
        data[rear] = null;

        if (size > 1) {
            rear = (rear - 1 + data.length) % data.length;
        }

        size--;
        return removedElement;
    }

    /**
     * Returns the first element without removing it.
     *
     * @return the first element, or null if the deque is empty
     */
    @Override
    public E peekFirst() {
        if (size == 0) {
            return null;
        }

        return (E) data[front];
    }

    /**
     * Returns the last element without removing it.
     *
     * @return the last element, or null if the deque is empty
     */
    @Override
    public E peekLast() {
        if (size == 0) {
            return null;
        }

        return (E) data[rear];
    }
}
