/*
 * Name: Rishit Rakapali
 * Email: rrakapali@ucsd.edu
 * TSN: 200440552
 * Sources Used: PA5 write-up, course materials
 *
 * This file implements a generic hash map using an array of
 * singly linked lists to handle collisions. It supports inserting,
 * retrieving, updating, and removing key-value pairs, as well as
 * expanding and rehashing the table when necessary.
 */

/**
 * Implements a generic hash map using separate chaining.
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values associated with the keys
 */
public class MyHashMap<K,V> {
    private static final int DEFAULT_CAPACITY = 5;
    private static final double LOAD_FACTOR = 0.8;
    private static final int EXPAND_CAPACITY_RATIO = 2;
    private static final int HASH_CODE_MASK = 0x7fffffff;

    Node[] hashTable;
    int size;

    /**
     * Creates an empty hash map with the default capacity.
     */
    public MyHashMap() {
        hashTable = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Creates an empty hash map with the specified capacity.
     *
     * @param initialCapacity the initial capacity of the hash table
     */
    public MyHashMap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException();
        }

        hashTable = new Node[initialCapacity];
        size = 0;
    }

    /**
     * Returns the value associated with the specified key.
     *
     * @param key the key to search for
     * @return the associated value, or null if the key does not exist
     */
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException();
        }

        int index = getHash(key, hashTable.length);

        Node currentNode = hashTable[index];

        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return (V) currentNode.getValue();
            }

            currentNode = currentNode.getNext();
        }


        return null;
    }

    /**
     * Inserts or updates a key-value pair in the hash map.
     * Expands the table if the load factor reaches 80%.
     *
     * @param key the key to insert or update
     * @param value the value to associate with the key
     * @return the previous value, or null if the key is new
     */
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException();
        }

        if (size >= LOAD_FACTOR * hashTable.length) {
            expandCapacity();
        }

        int index = getHash(key, hashTable.length);

        Node currentNode = hashTable[index];
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                V oldValue = (V) currentNode.getValue();
                currentNode.setValue(value);
                return oldValue;
            }

            currentNode = currentNode.getNext();
        }

        Node<K, V> newNode = new Node<K, V>(key, value);

        if (hashTable[index] == null) {
            hashTable[index] = newNode;
        } else {
            currentNode = hashTable[index];

            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }

            currentNode.setNext(newNode);
        }

        size++;

        return null;
    }

    /**
     * Removes the mapping associated with the specified key.
     * Updates the linked list and decreases the size if found.
     *
     * @param key the key to remove from the hash map
     * @return the removed value, or null if the key does not exist
     */
    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException();
        }

        int index = getHash(key, hashTable.length);

        Node currentNode = hashTable[index];
        Node previousNode = null;

        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                V oldValue = (V) currentNode.getValue();

                if (previousNode == null) {
                    hashTable[index] = currentNode.getNext();
                } else {
                    previousNode.setNext(currentNode.getNext());
                }

                size--;
                return oldValue;
            }

            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        return null;
    }

    /**
     * Returns the number of key-value pairs in the hash map.
     *
     * @return the number of stored mappings
     */
    public int size() {
        return size;
    }

    /**
     * Returns the current capacity of the hash table.
     *
     * @return the number of buckets in the hash table
     */
    public int getCapacity() {
        return hashTable.length;
    }

    /**
     * Removes all key-value pairs from the hash map.
     */
    public void clear() {
        for (int index = 0; index < hashTable.length; index++) {
            hashTable[index] = null;
        }

        size = 0;
    }

    /**
     * Checks whether the hash map contains any mappings.
     *
     * @return true if the hash map is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Doubles the capacity of the hash table and rehashes
     * all existing key-value pairs into the new table.
     */
    public void expandCapacity() {
        Node[] oldTable = hashTable;

        int newCapacity = hashTable.length * EXPAND_CAPACITY_RATIO;
        hashTable = new Node[newCapacity];

        for (int index = 0; index < oldTable.length; index++) {
            Node currentNode = oldTable[index];

            while (currentNode != null) {
                Node nextNode = currentNode.getNext();

                int newIndex = getHash( (K) currentNode.getKey(), newCapacity);

                currentNode.setNext(null);

                if (hashTable[newIndex] == null) {
                    hashTable[newIndex] = currentNode;
                } else {
                    Node lastNode = hashTable[newIndex];

                    while (lastNode.getNext() != null) {
                        lastNode = lastNode.getNext();
                    }

                    lastNode.setNext(currentNode);
                }

                currentNode = nextNode;
            }
        }
    }

    /**
     * Calculates the hash index for the specified key and capacity.
     *
     * @param key the key to calculate the hash for
     * @param capacity the capacity of the hash table
     * @return the calculated bucket index
     */
    public int getHash(K key, int capacity) {
        if (key == null) {
            throw new NullPointerException();
        }

        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        // Hashing function is given here. DO NOT MODIFY THIS
        // You do not need to understand how it works
        return (key.hashCode() & HASH_CODE_MASK) % capacity;
    }

    /**
     * A Node class that holds (key, value) pairs and references to the next node in the linked list
     */
    protected class Node<K,V> {
        K key;
        V value;
        Node next;

        /**
         * Constructor to create a single node
         * @param key key to store in this node
         * @param value value to store in this node
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        /**
         * Accessor to get the next node in the list
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the next node in the list
         * @param n the new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Accessor to get the node's key
         * @return this node's key
         */
        public K getKey() {
            return this.key;
        }

        /**
         * Set the node's key
         * @param key the new key
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * Accessor to get the node's value
         * @return this node's value
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Set the node's value
         * @param value the new value
         */
        public void setValue(V value) {
            this.value = value;
        }

        /**
         * Check if this node is equal to another node
         * @param other the other node to check equality with
         * @return whether or not this node is equal to the other node
         */
        public boolean equals(Node<K, V> other) {
            return this.key.equals(other.key) && this.value.equals(other.value);
        }
    }
}
