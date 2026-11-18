/*
    Name: Rishit Rakapali
    Email: rrakapali@ucsd.edu
    TSN: 200440552
    Sources Used: PA3 write-up, course materials

    This file implements a generic doubly linked list using sentinel head and
    tail nodes. It allows for insertion, removal, access, replacement, and 
    clearing of elements.
*/
import java.util.AbstractList;

/**
 * Implements a generic doubly linked list using sentinel nodes.
 * Maintains the head, tail and number of stored elements.
 */
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;	
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    //  Implementation of the MyLinkedList Class

    /**
     * Creates an empty doubly linked list with sentinel head and tail nodes.
     * Initializes size to zero and connects sentinel nodes together.
     */
    public MyLinkedList() {
        size = 0;
        head = new Node(null);
        tail = new Node(null);

        // Connecting sentinel nodes to represent an empty list
        head.setNext(tail);
        tail.setPrev(head);

    }

    /**
     * Returns the number of non-sentinel nodes in the linked list.
     * 
     * @return number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the element at the specified index in the linked list.
     * Throws an exception if index is outside valid range.
     * 
     * @param index the position of the element to access
     * @return the element stored at specified index
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node curr = getNth(index);

        return curr.getElement();
    }

    /**
     * Inserts a non-null element at the specified index to the linked list.
     * Updates the surrounding node references and increases the list size.
     * 
     * @param index the position where the element should be inserted into
     * @param data element to add to the list
     */
    @Override
    public void add(int index, E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node curr = head.getNext();

        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }

        Node insertNode = new Node(data);

        insertNode.setNext(curr);
        insertNode.setPrev(curr.getPrev());

        curr.getPrev().setNext(insertNode);
        curr.setPrev(insertNode);

        size++;
    }

    /**
     * Add a non-null node to the end of the LinkedList.
     * Updates the surrounding node references and list size
     * 
     * @param data element to add to the list
     * @return true if element was added successfully
     */
    @Override
    public boolean add(E data) {
        if (data == null) {
            throw new NullPointerException();
        }

        Node insertNode = new Node(data);

        // Connects new node between the old last node and tail
        insertNode.setNext(tail);
        insertNode.setPrev(tail.getPrev());

        tail.getPrev().setNext(insertNode);
        tail.setPrev(insertNode);

        size++;

        return true;
    }

    /**
     * Replaces the element at the specified index with new data.
     * Returns the previous element without changing list size.
     * 
     * @param index the position of the element to replace
     * @param data the new element to store
     * @return the element previously stored at the index
     */
    @Override
    public E set(int index, E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node curr = getNth(index);

        // Save the old element before replacing it.
        E oldElement = curr.getElement();

        curr.setElement(data);

        return oldElement;
    }

    /**
     * Removes the node at the specified index from the linked list.
     * Reconnects the surrounding nodes and decreases list size.
     * 
     * @param index the position of the element to remove
     * @return the element previously stored at index
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // Find the node to remove
        Node curr = getNth(index);

        E deletedElement = curr.getElement();

        // Bypass the removed node in both directions.
        curr.getPrev().setNext(curr.getNext());
        curr.getNext().setPrev(curr.getPrev());

        size--;

        return deletedElement;
    }

    /** 
     * Remove all non-sentinel nodes from the linked list.
     * Resets sentinel references and sets the size to zero.
     */
    @Override
    public void clear() {
        // Reconnects sentinel nodes to represent an empty list.
        head.setNext(tail);
        tail.setPrev(head);

        size = 0;
    }

    /**
     * Checks whether the linked list contains any elements.
     * 
     * @return true if list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }

        return false;
    }

    /**
     * Returns the node at the specified index in the linked list.
     * Throws an exception if the index is out of bounds.
     * 
     * @param index the position of the node to access
     * @return the node at the specified index
     */
    protected Node getNth(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // Traverse from the first node to the target index
        Node curr = head.getNext();
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }

        return curr;
    }
    
}
