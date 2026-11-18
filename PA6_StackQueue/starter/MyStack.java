/*
 * Name: Rishit Rakapali
 * Email: rrakapali@ucsd.edu
 * TSN: 200440552
 * Sources Used: PA6 write-up, course materials
 *
 * This file implements a stack using MyDeque as its backing structure.
 * Elements are added and removed in last-in, first-out order.
 */

/**
 * Implements a generic stack using the front of a MyDeque as its top.
 * Supports pushing, popping, peeking, and checking the stack size.
 *
 * @param <E> the type of elements stored in the stack
 */
public class MyStack<E> implements StackInterface<E> {
    MyDeque<E> theStack;

    /**
     * Creates an empty stack backed by a deque of the given capacity.
     *
     * @param initialCapacity the initial capacity of the backing deque
     */
    public MyStack(int initialCapacity) {
        theStack = new MyDeque<E>(initialCapacity);
    }

    /**
     * Checks whether the stack contains any elements.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean empty() {
        return theStack.size() == 0;
    }

    /**
     * Adds a non-null element to the top of the stack.
     *
     * @param element the element to push
     */
    @Override
    public void push(E element) {
        theStack.addFirst(element);
    }

    /**
     * Removes and returns the most recently pushed element.
     *
     * @return the previous top element, or null if the stack is empty
     */
    @Override
    public E pop() {
        return theStack.removeFirst();
    }

    /**
     * Returns the top element without removing it.
     *
     * @return the top element, or null if the stack is empty
     */
    @Override
    public E peek() {
        return theStack.peekFirst();
    }

    /**
     * Returns the number of elements currently stored in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return theStack.size();
    }

}
