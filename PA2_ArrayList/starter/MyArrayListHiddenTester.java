/*
 * Name: Rishit Rakapali
 * Email: rrakapali@ucsd.edu
 * TSN: 200440552
 * Sources Used: PA2 write-up, course materials
 *
 * This file contains JUnit tests for the MyArrayList class.
 * It tests constructors, adding and removing elements, invalid
 * indices, and capacity expansion.
 */

/**
 * JUnit tests for constructors, list operations, and capacity changes.
 */
import static org.junit.Assert.*;

import org.junit.*;

public class MyArrayListHiddenTester {
    // Do not change the method signatures!
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test
     */
    @Before
    public void setUp() throws Exception {

    }

    // ----------------MyArrayList class----------------

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test
    public void testConstructorInvalidArg() {
        try {
            new MyArrayList<Integer>(-1);
            fail("Negative capacity should throw an exception");
        } catch (IllegalArgumentException e) {
            // Expected exception.
        }
    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg() {
        Integer[] values = null;
        MyArrayList<Integer> list = new MyArrayList<>(values);
        assertEquals(0, list.size());
        assertEquals(5, list.getCapacity());
    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull() {
        Integer[] values = {1, null, 3};
        MyArrayList<Integer> list = new MyArrayList<>(values);
        assertEquals(3, list.size());
        assertEquals(3, list.getCapacity());
        assertArrayEquals(values, list.data);
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity() {
        Integer[] values = {1, 2, 3};
        MyArrayList<Integer> list = new MyArrayList<>(values);
        list.append(4);
        assertEquals(4, list.size());
        assertEquals(6, list.getCapacity());
        assertArrayEquals(new Integer[] {1, 2, 3, 4, null, null},
                list.data);
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull() {
        Integer[] values = {1, 2};
        MyArrayList<Integer> list = new MyArrayList<>(values);
        list.append(null);
        assertEquals(3, list.size());
        assertEquals(4, list.getCapacity());
        assertNull(list.get(2));
    }

    /**
     * Aims to test the add method when input index is out of bounds
     */
    @Test
    public void testAddOutOfBounds() {
        MyArrayList<Integer> list = new MyArrayList<>();
        try {
            list.add(-1, 3);
            fail("Negative index should throw an exception");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception.
        }
        try {
            list.add(1, 3);
            fail("Index above size should throw an exception");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception.
        }
        assertEquals(0, list.size());
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testAddMultiple() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i, i);
        }
        assertEquals(1000, list.size());
        assertTrue(list.getCapacity() >= 1000);
        for (int i = 0; i < 1000; i++) {
            assertEquals(Integer.valueOf(i), list.get(i));
        }
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test
    public void testGetOutOfBound() {
        MyArrayList<Integer> list = new MyArrayList<>(
                new Integer[] {1, 2});
        try {
            list.get(-1);
            fail("Negative index should throw an exception");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception.
        }
        try {
            list.get(2);
            fail("Index at size should throw an exception");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception.
        }
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test
    public void testSetOutOfBound() {
        MyArrayList<Integer> list = new MyArrayList<>(
                new Integer[] {1, 2});
        try {
            list.set(-1, 5);
            fail("Negative index should throw an exception");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception.
        }
        try {
            list.set(2, 5);
            fail("Index at size should throw an exception");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception.
        }
        assertArrayEquals(new Integer[] {1, 2}, list.data);
    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple() {
        MyArrayList<Integer> list = new MyArrayList<>(
                new Integer[] {1, 2, 3, 4});
        assertEquals(Integer.valueOf(2), list.remove(1));
        assertEquals(Integer.valueOf(3), list.remove(1));
        assertEquals(2, list.size());
        assertEquals(4, list.getCapacity());
        assertArrayEquals(new Integer[] {1, 4, null, null},
                list.data);
    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test
    public void testRemoveOutOfBound() {
        MyArrayList<Integer> list = new MyArrayList<>(
                new Integer[] {1, 2});
        try {
            list.remove(-1);
            fail("Negative index should throw an exception");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception.
        }
        try {
            list.remove(2);
            fail("Index at size should throw an exception");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception.
        }
        assertEquals(2, list.size());
    }

    /**
     * Aims to test the expandCapacity method when
     * requiredCapacity is strictly less than the current capacity
     */
    @Test
    public void testExpandCapacitySmaller() {
        MyArrayList<Integer> list = new MyArrayList<>(5);
        try {
            list.expandCapacity(4);
            fail("Smaller capacity should throw an exception");
        } catch (IllegalArgumentException e) {
            // Expected exception.
        }
        assertEquals(5, list.getCapacity());
    }

    /**
     * Aims to test the expandCapacity method when
     * requiredCapacity is greater than twice the current capacity
     * and greater than the default capacity
     */
    @Test
    public void testExpandCapacityLarge() {
        MyArrayList<Integer> list = new MyArrayList<>(
                new Integer[] {1, 2, 3});
        list.expandCapacity(20);
        assertEquals(20, list.getCapacity());
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(2));
    }
}
