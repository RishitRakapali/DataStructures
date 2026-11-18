/*
 * Name: Rishit Rakapali
 * Email: rrakapali@ucsd.edu
 * TSN: 200440552
 * Sources Used: PA6 write-up, course materials
 *
 * This file tests deque, stack, queue, and bracket matching behavior.
 * Tests cover circular wraparound, capacity expansion, and empty cases.
 */

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Contains custom JUnit tests for the four PA6 implementations.
 * Includes boundary cases not covered by the public tests.
 */
public class CustomTester {

    /** Tests valid, zero, and negative initial deque capacities. */
    @Test
    public void testDequeConstructor() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        assertEquals(3, deque.data.length);
        assertEquals(0, deque.size());
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);

        MyDeque<Integer> zeroCapacityDeque = new MyDeque<>(0);
        assertEquals(0, zeroCapacityDeque.data.length);

        try {
            new MyDeque<Integer>(-1);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException exception) {
            // A negative capacity must be rejected.
        }
    }

    /** Tests expansion from zero capacity and from an empty deque. */
    @Test
    public void testDequeExpandFromZero() {
        MyDeque<String> deque = new MyDeque<>(0);
        deque.expandCapacity();
        assertEquals(10, deque.data.length);
        assertEquals(0, deque.size());
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);
        deque.addLast("first");
        assertEquals("first", deque.peekFirst());
    }

    /** Tests expansion when the circular array has wrapped around. */
    @Test
    public void testDequeExpandWrappedOrder() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        assertEquals(Integer.valueOf(1), deque.removeFirst());
        assertEquals(Integer.valueOf(2), deque.removeFirst());
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);

        assertEquals(2, deque.front);
        assertEquals(1, deque.rear);
        deque.expandCapacity();

        assertEquals(10, deque.data.length);
        assertEquals(5, deque.size());
        assertEquals(0, deque.front);
        assertEquals(4, deque.rear);
        assertArrayEquals(new Object[] {3, 4, 5, 6, 7,
                null, null, null, null, null}, deque.data);
    }

    /** Tests that addFirst wraps and grows when the deque is full. */
    @Test
    public void testDequeAddFirstExpansion() {
        MyDeque<Integer> deque = new MyDeque<>(2);
        deque.addFirst(20);
        deque.addFirst(10);
        assertEquals(1, deque.front);
        assertEquals(0, deque.rear);

        deque.addFirst(5);
        assertEquals(4, deque.data.length);
        assertEquals(3, deque.size());
        assertEquals(3, deque.front);
        assertEquals(Integer.valueOf(5), deque.peekFirst());
        assertEquals(Integer.valueOf(20), deque.peekLast());
        assertEquals(Integer.valueOf(5), deque.removeFirst());
        assertEquals(Integer.valueOf(10), deque.removeFirst());
        assertEquals(Integer.valueOf(20), deque.removeFirst());
    }

    /** Tests that addLast wraps and grows when the deque is full. */
    @Test
    public void testDequeAddLastExpansion() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(Integer.valueOf(1), deque.removeFirst());
        deque.addLast(3);
        deque.addLast(4);
        assertEquals(1, deque.front);
        assertEquals(0, deque.rear);

        deque.addLast(5);
        assertEquals(6, deque.data.length);
        assertEquals(4, deque.size());
        assertEquals(0, deque.front);
        assertEquals(3, deque.rear);
        assertArrayEquals(new Object[] {2, 3, 4, 5, null, null},
                deque.data);
    }

    /** Tests that null insertions do not change deque contents. */
    @Test
    public void testDequeRejectsNull() {
        MyDeque<Integer> deque = new MyDeque<>(2);
        deque.addLast(10);

        try {
            deque.addFirst(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException exception) {
            // Null elements are forbidden.
        }

        try {
            deque.addLast(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException exception) {
            // Null elements are forbidden.
        }

        assertEquals(1, deque.size());
        assertEquals(Integer.valueOf(10), deque.peekFirst());
    }

    /** Tests removing the front element when indices wrap around. */
    @Test
    public void testDequeRemoveFirstWrapped() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);

        assertEquals(Integer.valueOf(1), deque.removeFirst());
        assertEquals(0, deque.front);
        assertEquals(Integer.valueOf(2), deque.peekFirst());
        assertEquals(2, deque.size());
        assertNull(deque.data[2]);
        assertEquals(Integer.valueOf(2), deque.removeFirst());
        assertEquals(Integer.valueOf(3), deque.removeFirst());
    }

    /** Tests removing the rear element across index zero. */
    @Test
    public void testDequeRemoveLastWrapped() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertEquals(Integer.valueOf(1), deque.removeFirst());
        deque.addLast(4);

        assertEquals(0, deque.rear);
        assertEquals(Integer.valueOf(4), deque.removeLast());
        assertEquals(2, deque.rear);
        assertNull(deque.data[0]);
        assertEquals(Integer.valueOf(3), deque.removeLast());
        assertEquals(Integer.valueOf(2), deque.removeLast());
    }

    /** Tests that removing the final element keeps both indices unchanged. */
    @Test
    public void testDequeRemoveFinalElement() {
        MyDeque<Integer> deque = new MyDeque<>(4);
        deque.addLast(10);
        deque.addLast(20);
        assertEquals(Integer.valueOf(10), deque.removeFirst());
        assertEquals(1, deque.front);
        assertEquals(1, deque.rear);

        assertEquals(Integer.valueOf(20), deque.removeLast());
        assertEquals(0, deque.size());
        assertEquals(1, deque.front);
        assertEquals(1, deque.rear);
        deque.addFirst(30);
        assertEquals(Integer.valueOf(30), deque.peekLast());
        assertEquals(1, deque.front);
        assertEquals(1, deque.rear);
    }

    /** Tests empty deque removals and peeks. */
    @Test
    public void testDequeEmptyOperations() {
        MyDeque<String> deque = new MyDeque<>(1);
        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
        assertNull(deque.peekFirst());
        assertNull(deque.peekLast());
        assertEquals(0, deque.size());

        deque.addFirst("entry");
        assertEquals("entry", deque.removeFirst());
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);
        assertNull(deque.peekFirst());
    }

    /** Tests stack last-in, first-out behavior and automatic expansion. */
    @Test
    public void testStackLastInFirstOut() {
        MyStack<Integer> stack = new MyStack<>(1);
        assertTrue(stack.empty());
        assertNull(stack.pop());
        assertNull(stack.peek());

        stack.push(10);
        stack.push(20);
        stack.push(30);
        assertEquals(3, stack.size());
        assertEquals(Integer.valueOf(30), stack.peek());
        assertEquals(Integer.valueOf(30), stack.pop());
        assertEquals(Integer.valueOf(20), stack.pop());
        assertEquals(Integer.valueOf(10), stack.pop());
        assertNull(stack.pop());
        assertTrue(stack.empty());
    }

    /** Tests queue first-in, first-out behavior and automatic expansion. */
    @Test
    public void testQueueFirstInFirstOut() {
        MyQueue<Integer> queue = new MyQueue<>(1);
        assertTrue(queue.empty());
        assertNull(queue.dequeue());
        assertNull(queue.peek());

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals(3, queue.size());
        assertEquals(Integer.valueOf(10), queue.peek());
        assertEquals(Integer.valueOf(10), queue.dequeue());
        assertEquals(Integer.valueOf(20), queue.dequeue());
        assertEquals(Integer.valueOf(30), queue.dequeue());
        assertNull(queue.dequeue());
        assertTrue(queue.empty());
    }

    /** Tests null rejection and invalid capacities in stack and queue. */
    @Test
    public void testStackAndQueueExceptions() {
        MyStack<Integer> stack = new MyStack<>(0);
        MyQueue<Integer> queue = new MyQueue<>(0);

        try {
            stack.push(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException exception) {
            // A stack cannot contain null.
        }

        try {
            queue.enqueue(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException exception) {
            // A queue cannot contain null.
        }

        assertTrue(stack.empty());
        assertTrue(queue.empty());
    }

    /** Tests correctly nested brackets and unrelated characters. */
    @Test
    public void testAlgorithmValidBrackets() {
        assertTrue(MyAlgorithm.isValidBrackets(""));
        assertTrue(MyAlgorithm.isValidBrackets("plain text 123"));
        assertTrue(MyAlgorithm.isValidBrackets("({}[])"));
        assertTrue(MyAlgorithm.isValidBrackets("a[b(c){d}e]f"));
        assertTrue(MyAlgorithm.isValidBrackets("()[]{}()"));
    }

    /** Tests mismatched, crossed, and unbalanced brackets. */
    @Test
    public void testAlgorithmInvalidBrackets() {
        assertFalse(MyAlgorithm.isValidBrackets("("));
        assertFalse(MyAlgorithm.isValidBrackets(")"));
        assertFalse(MyAlgorithm.isValidBrackets(")("));
        assertFalse(MyAlgorithm.isValidBrackets("([)]"));
        assertFalse(MyAlgorithm.isValidBrackets("((]]"));
        assertFalse(MyAlgorithm.isValidBrackets("[a{b]c}"));
        assertFalse(MyAlgorithm.isValidBrackets("(()"));
    }

    /** Tests rejection of a null bracket string. */
    @Test
    public void testAlgorithmNullInput() {
        try {
            MyAlgorithm.isValidBrackets(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException exception) {
            // A null string is not valid input.
        }
    }
}
