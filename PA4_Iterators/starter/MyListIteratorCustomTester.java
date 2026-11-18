/*
    Name: Rishit Rakapali
    Email: rrakapali@ucsd.edu
    TSN: 200440552
    Sources Used: PA4 write-up, course materials

    This file contains custom JUnit tests for MyListIterator.
    It tests iterator boundaries, invalid operations, and index methods.
*/

import static org.junit.Assert.*;
import org.junit.*;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyListIteratorCustomTester {

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test
    public void testNextEnd() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(10);
        list.add(20);

        ListIterator<Integer> iter = list.listIterator();

        iter.next();
        iter.next();

        try {
            iter.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected exception.
        }
    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test
    public void testPreviousStart() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(10);
        list.add(20);

        ListIterator<Integer> iter = list.listIterator();

        try {
            iter.previous();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected exception.
        }

        assertEquals(0, iter.nextIndex());
        assertFalse(iter.hasPrevious());
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test
    public void testAddInvalid() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(10);
        list.add(20);

        ListIterator<Integer> iter = list.listIterator();

        try {
            iter.add(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception.
        }

        // Verify the list remains unchanged.
        assertEquals(2, list.size());
        assertEquals(Integer.valueOf(10), list.get(0));
        assertEquals(Integer.valueOf(20), list.get(1));
    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test
    public void testCantSet() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(10);
        list.add(20);

        ListIterator<Integer> iter = list.listIterator();

        try {
            iter.set(30);
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected exception.
        }

        // Verify the list remains unchanged.
        assertEquals(2, list.size());
        assertEquals(Integer.valueOf(10), list.get(0));
        assertEquals(Integer.valueOf(20), list.get(1));
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test
    public void testCantRemove() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(10);
        list.add(20);

        ListIterator<Integer> iter = list.listIterator();

        try {
            iter.remove();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected exception.
        }

        // Verify the list remains unchanged.
        assertEquals(2, list.size());
        assertEquals(Integer.valueOf(10), list.get(0));
        assertEquals(Integer.valueOf(20), list.get(1));
    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(10);
        list.add(20);

        ListIterator<Integer> iter = list.listIterator();

        iter.next();
        iter.next();

        assertFalse(iter.hasNext());
        assertEquals(2, iter.nextIndex());
        assertEquals(2, list.size());
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(10);
        list.add(20);

        ListIterator<Integer> iter = list.listIterator();

        assertFalse(iter.hasPrevious());
        assertEquals(-1, iter.previousIndex());
        assertEquals(0, iter.nextIndex());
    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(10);
        list.add(20);

        ListIterator<Integer> iter = list.listIterator();

        assertEquals(-1, iter.previousIndex());
        assertEquals(0, iter.nextIndex());
        assertFalse(iter.hasPrevious());
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(10);
        list.add(20);
        list.add(30);

        ListIterator<Integer> iter = list.listIterator();

        iter.next();
        iter.next();
        iter.next();

        assertEquals(3, iter.nextIndex());
        assertEquals(2, iter.previousIndex());
        assertFalse(iter.hasNext());
        assertEquals(3, list.size());
    }
}
