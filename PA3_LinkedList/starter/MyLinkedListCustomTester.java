/*
 * Name: Rishit Rakapali
 * Email: rrakapali@ucsd.edu
 * TSN: 200440552
 * Sources Used: PA3 write-up, course materials
 *
 * This file contains JUnit tests for the MyLinkedList and MyAlgorithm
 * classes. It tests insertion, removal, replacement, and merging
 * linked lists.
 */

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Tests the functionality of MyLinkedList and MyAlgorithm
 * using custom JUnit test cases.
 */
public class MyLinkedListCustomTester {

	// Optional: add test variables here

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
		// Optional: add setup here
	}

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {
		MyLinkedList<Integer> list = new MyLinkedList<>();

		assertTrue(list.add(10));
		assertTrue(list.add(20));

		assertEquals(2, list.size());
		assertEquals(Integer.valueOf(10), list.get(0));
		assertEquals(Integer.valueOf(20), list.get(1));

		// Check the first and last node connections.
		assertEquals(list.head, list.head.getNext().getPrev());
		assertEquals(list.tail, list.tail.getPrev().getNext());
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {
   		MyLinkedList<Integer> list = new MyLinkedList<>();
			
		list.add(10);
		list.add(20);

		// Insert an element at the beginning.
		list.add(0, 5);

		// Check the size and element order.
		assertEquals(3, list.size());
		assertEquals(Integer.valueOf(5), list.get(0));
		assertEquals(Integer.valueOf(10), list.get(1));
		assertEquals(Integer.valueOf(20), list.get(2));

		// Check the node references near the beginning.
		assertEquals(list.head, list.head.getNext().getPrev());
		assertEquals(list.getNth(0), list.head.getNext());
		assertEquals(list.getNth(1), list.getNth(0).getNext());
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {
	    MyLinkedList<Integer> list = new MyLinkedList<>();

		list.add(10);
		list.add(20);
		list.add(30);

		list.add(1, 15);

		assertEquals(4, list.size());
		assertEquals(Integer.valueOf(10), list.get(0));
		assertEquals(Integer.valueOf(15), list.get(1));
		assertEquals(Integer.valueOf(20), list.get(2));
		assertEquals(Integer.valueOf(30), list.get(3));

		// Verify the new node's forward and backward connections.
		assertEquals(list.getNth(0), list.getNth(1).getPrev());
		assertEquals(list.getNth(2), list.getNth(1).getNext());

		// Verify neighboring nodes point to the inserted node.
		assertEquals(list.getNth(1), list.getNth(0).getNext());
		assertEquals(list.getNth(1), list.getNth(2).getPrev());
	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test
	public void testCustomRemoveFromEmpty() {
	    MyLinkedList<Integer> list = new MyLinkedList<>();

		assertEquals(0, list.size());

		try {
			// Fail test if successfully removed a node
			list.remove(0);
			fail("Expected IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Expected exception.
		}

		assertEquals(0, list.size());
		assertEquals(list.tail, list.head.getNext());
		assertEquals(list.head, list.tail.getPrev());
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {
	    MyLinkedList<Integer> list = new MyLinkedList<>();

		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);

		assertEquals(Integer.valueOf(20), list.remove(1));

		assertEquals(3, list.size());
		assertEquals(Integer.valueOf(10), list.get(0));
		assertEquals(Integer.valueOf(30), list.get(1));
		assertEquals(Integer.valueOf(40), list.get(2));

		// Verify the surrounding nodes are connected correctly.
		assertEquals(list.getNth(1), list.getNth(0).getNext());
		assertEquals(list.getNth(0), list.getNth(1).getPrev());
	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test
	public void testCustomSetIdxOutOfBounds() {
		MyLinkedList<Integer> list = new MyLinkedList<>();

		list.add(10);
		list.add(20);

		try {
			list.set(2, 30);
			fail("Expected IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Expected exception.
		}

		assertEquals(2, list.size());
		assertEquals(Integer.valueOf(10), list.get(0));
		assertEquals(Integer.valueOf(20), list.get(1));
	}

	/**
	 * Aims to test the mergeLists(MyLinkedList<E> left, MyLinkedList<E> right)
	 * method.
	 * Merge lists when one is longer than the other. 
	 */
	@Test
	public void testCustomMergeUnequalSize() {
		MyLinkedList<Integer> left = new MyLinkedList<>();
		MyLinkedList<Integer> right = new MyLinkedList<>();

		left.add(1);
		left.add(3);
		left.add(5);

		right.add(2);
		right.add(4);
		right.add(6);
		right.add(8);

		MyLinkedList<Integer> merged = MyAlgorithm.mergeLists(left, right);

		assertEquals(7, merged.size());
		assertEquals(Integer.valueOf(1), merged.get(0));
		assertEquals(Integer.valueOf(2), merged.get(1));
		assertEquals(Integer.valueOf(3), merged.get(2));
		assertEquals(Integer.valueOf(4), merged.get(3));
		assertEquals(Integer.valueOf(5), merged.get(4));
		assertEquals(Integer.valueOf(6), merged.get(5));
		assertEquals(Integer.valueOf(8), merged.get(6));

		// Verify that the original lists remain unchanged.
		assertEquals(3, left.size());
		assertEquals(4, right.size());
	}

	/**
	 * Aims to test the mergeLists(MyLinkedList<E> left, MyLinkedList<E> right)
	 * method.
	 * Merge lists when both lists share a value.
	 */
	@Test
	public void testCustomMergeDuplicates() {
		MyLinkedList<Integer> left = new MyLinkedList<>();
		MyLinkedList<Integer> right = new MyLinkedList<>();

		left.add(1);
		left.add(3);
		left.add(5);

		right.add(2);
		right.add(3);
		right.add(5);

		MyLinkedList<Integer> merged = MyAlgorithm.mergeLists(left, right);

		assertEquals(6, merged.size());
		assertEquals(Integer.valueOf(1), merged.get(0));
		assertEquals(Integer.valueOf(2), merged.get(1));
		assertEquals(Integer.valueOf(3), merged.get(2));
		assertEquals(Integer.valueOf(3), merged.get(3));
		assertEquals(Integer.valueOf(5), merged.get(4));
		assertEquals(Integer.valueOf(5), merged.get(5));
	}

}
