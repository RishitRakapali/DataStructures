/*
    Name: Rishit Rakapali
    Email: rrakapali@ucsd.edu
    TSN: 200440552
    Sources Used: PA4 write-up, course materials

    This file implements an algorithm to merge two sorted linked lists
    using iterators. It preserves duplicate elements, handles lists of
    unequal sizes, and runs in O(n) time.
*/
import java.util.ListIterator;

/**
 * Provides an algorithm for merging two sorted linked lists.
 */
public class MyAlgorithm {

    /**
     * Merges two sorted linked lists using iterators.
     * Preserves duplicate elements and handles unequal list sizes.
     *
     * @param <E> generic type supporting comparison
     * @param left the first sorted linked list
     * @param right the second sorted linked list
     * @return a new sorted linked list containing all elements
     */
    public static <E extends Comparable<E>> MyLinkedList<E> mergeLists(
            MyLinkedList<E> left, MyLinkedList<E> right) {

        if (left == null || right == null) {
            throw new NullPointerException();
        }

        MyLinkedList<E> sortedList = new MyLinkedList<E>();

        // Create iterators for both input lists and the result.
        ListIterator<E> leftIter = left.listIterator();
        ListIterator<E> rightIter = right.listIterator();
        ListIterator<E> resultIter = sortedList.listIterator();

        E leftValue = null;
        E rightValue = null;

        // Get the first element from each list.
        if (leftIter.hasNext()) {
            leftValue = leftIter.next();
        }

        if (rightIter.hasNext()) {
            rightValue = rightIter.next();
        }

        // Merge elements by comparing values from both lists.
        while (leftValue != null && rightValue != null) {
            if (leftValue.compareTo(rightValue) <= 0) {
                resultIter.add(leftValue);

                if (leftIter.hasNext()) {
                    leftValue = leftIter.next();
                } else {
                    leftValue = null;
                }
            } else {
                resultIter.add(rightValue);

                if (rightIter.hasNext()) {
                    rightValue = rightIter.next();
                } else {
                    rightValue = null;
                }
            }
        }

        // Add remaining elements from the left list.
        while (leftValue != null) {
            resultIter.add(leftValue);

            if (leftIter.hasNext()) {
                leftValue = leftIter.next();
            } else {
                leftValue = null;
            }
        }

        // Add remaining elements from the right list.
        while (rightValue != null) {
            resultIter.add(rightValue);

            if (rightIter.hasNext()) {
                rightValue = rightIter.next();
            } else {
                rightValue = null;
            }
        }

        return sortedList;
    }
}