/*
    Name: Rishit Rakapali
    Email: rrakapali@ucsd.edu
    TSN: 200440552
    Sources Used: PA3 write-up, course materials

    This file implements an algorithm to merge two sorted linked lists into
    a new sorted linked list while preserving duplicate elements.
*/

/**
 * Provides an algorithm for merging two sorted linked lists.
 * Preserves duplicate elements in the resulting sorted list.
 */
public class MyAlgorithm {

    /**
     * Merges two sorted linked lists into a new sorted linked list.
     * Preserves duplicate elements and handles lists of unequal sizes.
     * 
     * @param <E> generic type to allow Integers, Strings, etc.
     * @param left the first sorted linked list.
     * @param right the second sorted linked list.
     * @return a new sorted linked list containing all elements.
     */
    public static <E extends Comparable<E>> MyLinkedList<E> mergeLists(
            MyLinkedList<E> left, MyLinkedList<E> right) {

        if (left == null || right == null) {
            throw new NullPointerException();
        }

        MyLinkedList<E> sortedList = new MyLinkedList<E>();

        int leftIndex = 0;
        int rightIndex = 0;

        // Compare elements from both lists and add the smaller one.
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) <= 0) {
                sortedList.add(left.get(leftIndex));
                leftIndex++;
            } else {
                sortedList.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        // Add remaining elements from the left list.
        while (leftIndex < left.size()) {
            sortedList.add(left.get(leftIndex));
            leftIndex++;
        }

        // Add remaining elements from the right list.
        while (rightIndex < right.size()) {
            sortedList.add(right.get(rightIndex));
            rightIndex++;
        }

            return sortedList;
        }
}