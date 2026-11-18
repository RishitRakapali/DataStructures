
/*
 * Name: Rishit Rakapali
 * Email: rrakapali@ucsd.edu
 * TSN: 200440552
 * Sources Used: PA5 write-up, course materials
 *
 * This file implements a generic hash set using MyHashMap.
 * It supports adding, removing, and checking for unique
 * elements, along with basic set operations.
 */

/**
 * Implements a generic hash set using a hash map.
 *
 * @param <E> the type of elements stored in the set
 */
public class MyHashSet<E> {

    public static final Object DEFAULT_OBJECT = new Object();

    MyHashMap<E, Object> hashMap;

    /**
     * Creates an empty hash set with the default capacity.
     */
    public MyHashSet() {
        hashMap = new MyHashMap<E, Object>();
    }

    /**
     * Creates an empty hash set with the specified capacity.
     *
     * @param initialCapacity the initial capacity of the hash set
     */
    public MyHashSet(int initialCapacity) {
        hashMap = new MyHashMap<E, Object>(initialCapacity);
    }

    /**
     * Adds an element to the set if it does not already exist.
     *
     * @param element the element to add
     * @return true if the element was new, false otherwise
     */
    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException();
        }

        Object oldValue = hashMap.put(element, DEFAULT_OBJECT);

        return oldValue == null;
    }

    /**
     * Removes the specified element from the set.
     *
     * @param element the element to remove
     * @return true if the element existed, false otherwise
     */
    public boolean remove(E element) {
        if (element == null) {
            throw new NullPointerException();
        }

        Object oldValue = hashMap.remove(element);

        return oldValue != null;
    }

    /**
     * Checks whether the set contains the specified element.
     *
     * @param element the element to search for
     * @return true if the element exists, false otherwise
     */
    public boolean contains(E element) {
        if (element == null) {
            throw new NullPointerException();
        }

        return hashMap.get(element) != null;
    }

    /**
     * Returns the number of elements in the hash set.
     *
     * @return the number of stored elements
     */
    public int size() {
        return hashMap.size();
    }

    /**
     * Removes all elements from the hash set.
     */
    public void clear() {
        hashMap.clear();
    }

    /**
     * Checks whether the hash set contains any elements.
     *
     * @return true if the set is empty, false otherwise
     */
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }
}
