/*
 * Name: Rishit Rakapali
 * Email: rrakapali@ucsd.edu
 * TSN: 200440552
 * Sources Used: PA2 write-up, course materials
 *
 * This file implements an ArrayList using an object array.
 * It supports inserting, removing, and accessing elements, as well
 * as expanding the capacity.
 */

/**
 * A list that stores elements in an array and grows when needed.
 *
 * @param <E> the type of elements stored in this list
 */
public class MyArrayList<E> implements MyList<E> {
    private static final int DEFAULT_CAPACITY = 5;
    private static final int GROWTH_FACTOR = 2;

    Object[] data;
    int size;

    /** Creates an empty list with the default capacity. */
    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Creates an empty list with the given capacity.
     *
     * @param initialCapacity the starting capacity
     * @throws IllegalArgumentException if capacity is negative
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        size = 0;
    }

    /**
     * Creates a list containing a copy of an array.
     *
     * @param arr the array to copy, or null for an empty default list
     */
    public MyArrayList(E[] arr) {
        if (arr == null) {
            data = new Object[DEFAULT_CAPACITY];
            size = 0;
        } else {
            data = new Object[arr.length];
            for (int i = 0; i < arr.length; i++) {
                data[i] = arr[i];
            }
            size = arr.length;
        }
    }

    /**
     * Expands the underlying array while keeping existing elements.
     *
     * @param requiredCapacity the minimum capacity needed
     * @throws IllegalArgumentException if smaller than current capacity
     */
    public void expandCapacity(int requiredCapacity) {
        if (requiredCapacity < data.length) {
            throw new IllegalArgumentException();
        }

        int newCapacity = data.length * GROWTH_FACTOR;
        if (data.length == 0) {
            newCapacity = DEFAULT_CAPACITY;
        }
        if (newCapacity < requiredCapacity) {
            newCapacity = requiredCapacity;
        }

        Object[] newData = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * Gets the length of the underlying array.
     *
     * @return the current capacity
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * Inserts an element at an index, shifting later elements right.
     *
     * @param index the insertion position
     * @param element the element to insert
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == data.length) {
            expandCapacity(size + 1);
        }

        // Move elements right to make room for the new element.
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element the element to append
     */
    public void append(E element) {
        if (size == data.length) {
            expandCapacity(size + 1);
        }
        data[size] = element;
        size++;
    }

    /**
     * Gets an element at a given index.
     *
     * @param index the position to read
     * @return the element at the index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) data[index];
    }

    /**
     * Replaces an element at an index.
     *
     * @param index the position to replace
     * @param element the new element
     * @return the old element
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = (E) data[index];
        data[index] = element;
        return oldElement;
    }

    /**
     * Removes an element and shifts remaining elements to the left.
     *
     * @param index the position to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E removedElement = (E) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;
        return removedElement;
    }

    /**
     * Gets the number of elements in the list.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }
}
