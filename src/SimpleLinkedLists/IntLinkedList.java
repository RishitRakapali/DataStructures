package SimpleLinkedLists;

/**
 * A singly linked list that stores only int values.
 *
 * No generics, no Comparable — because every value is an int, we can
 * compare with == and store the primitive directly. This keeps the
 * focus on the pointer logic that makes a linked list work.
 */
public class IntLinkedList {

    /**
     * A single link in the chain.
     * Holds one int and a reference to the next Node (null if it's the last).
     *
     * It's 'static' because a Node doesn't need access to the outer list's
     * fields — it's just a self-contained box. It's 'private' because the
     * outside world should go through IntLinkedList's methods, not touch Nodes.
     */
    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;   // not linked to anything yet
        }
    }

    private Node head;   // first node in the list; null when the list is empty
    private int size;    // how many nodes we have (kept in sync so size() is O(1))

    // ---------- Adding ----------

    /** Add a value to the FRONT of the list. O(1). */
    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;   // new node points at the old first node
        head = newNode;        // list now starts at the new node
        size++;
    }

    /** Add a value to the END of the list. O(n) because we walk to the tail. */
    public void add(int value) {
        Node newNode = new Node(value);

        if (head == null) {        // empty list: new node becomes the head
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {   // walk until the last node
                current = current.next;
            }
            current.next = newNode;          // link it on the end
        }
        size++;
    }

    // ---------- Reading ----------

    /** Return the value at a given index. O(n). Throws if index is invalid. */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {   // step forward 'index' times
            current = current.next;
        }
        return current.value;
    }

    /** True if the value appears anywhere in the list. O(n). */
    public boolean contains(int value) {
        Node current = head;
        while (current != null) {
            if (current.value == value) {   // == is fine for ints
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // ---------- Removing ----------

    /**
     * Remove the FIRST node whose value matches.
     * Returns true if something was removed. O(n).
     */
    public boolean remove(int value) {
        if (head == null) {
            return false;
        }

        // Special case: the value is in the head node.
        if (head.value == value) {
            head = head.next;   // drop the first node
            size--;
            return true;
        }

        // Otherwise walk with a 'previous' pointer so we can re-link around
        // the node we delete.
        Node previous = head;
        Node current = head.next;
        while (current != null) {
            if (current.value == value) {
                previous.next = current.next;   // skip over 'current'
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;   // value not found
    }

    // ---------- Utility ----------

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** Builds a string like [3, 7, 1] so the list is easy to print. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // ---------- Quick demo ----------

    public static void main(String[] args) {
        IntLinkedList list = new IntLinkedList();

        list.add(3);        // [3]
        list.add(7);        // [3, 7]
        list.add(1);        // [3, 7, 1]
        list.addFirst(9);   // [9, 3, 7, 1]
        System.out.println(list);            // [9, 3, 7, 1]

        System.out.println(list.get(2));     // 7
        System.out.println(list.contains(7)); // true
        System.out.println(list.size());     // 4

        list.remove(7);     // [9, 3, 1]
        System.out.println(list);            // [9, 3, 1]
        System.out.println(list.contains(7)); // false
    }
}
