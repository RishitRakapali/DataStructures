/*
 * Name: Rishit Rakapali
 * Email: rrakapali@ucsd.edu
 * TSN: 200440552
 * Sources Used: PA6 write-up, course materials
 *
 * This file implements a queue using MyDeque as its backing structure.
 * Elements are added and removed in first-in, first-out order.
 */

/**
 * Implements a generic queue using a MyDeque for storage.
 * Inserts at the rear and removes from the front.
 *
 * @param <E> the type of elements stored in the queue
 */
public class MyQueue<E> implements QueueInterface<E> {
    MyDeque<E> theQueue;

    /**
     * Creates an empty queue backed by a deque of the given capacity.
     *
     * @param initialCapacity the initial capacity of the backing deque
     */
    public MyQueue(int initialCapacity) {
        theQueue = new MyDeque<E>(initialCapacity);
    }

    /**
     * Checks whether the queue contains any elements.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean empty() {
        return theQueue.size() == 0;
    }

    /**
     * Adds a non-null element to the rear of the queue.
     *
     * @param element the element to enqueue
     */
    @Override
    public void enqueue(E element) {
        theQueue.addLast(element);
    }

    /**
     * Removes and returns the oldest element in the queue.
     *
     * @return the previous front element, or null if the queue is empty
     */
    @Override
    public E dequeue() {
        return theQueue.removeFirst();
    }

    /**
     * Returns the next element to be dequeued without removing it.
     *
     * @return the front element, or null if the queue is empty
     */
    @Override
    public E peek() {
        return theQueue.peekFirst();
    }

    /**
     * Returns the number of elements currently stored in the queue.
     *
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return theQueue.size();
    }
}
