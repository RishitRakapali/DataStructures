
/*
 * Name: Rishit Rakapali
 * Email: rrakapali@ucsd.edu
 * PID: 200440552
 * Sources Used: JDK 17 Doc
 *
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of Student/Course/Sanctuary. You will only
 * receive points if your test passes when run on a good implementation and
 * fails for the corresponding bad implementation.
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * The custom tester for PA5, which covers some basic test cases.
 */
public class CustomTester {
    // Optional: add test variables here

    /**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
    @Before
    public void setup() {
        // Optional: add setup here
    }

    // ----------------MyHashMap class----------------

    /**
     * Test MyHashMap constructor with an invalid initial capacity
     */
    @Test
    public void testMyHashMapConstructorInvalidCapacity() {
        try {
            new MyHashMap<Integer, String>(0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        try {
            new MyHashMap<Integer, String>(-5);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Test MyHashMap get with a null key
     */
    @Test
    public void testMyHashMapGetNullKey() {
        MyHashMap<Integer, String> map = new MyHashMap<>();

        map.put(1, "Apple");

        try {
            map.get(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
        }

        assertEquals(1, map.size());
        assertEquals("Apple", map.get(1));
    }

    /**
     * Test MyHashMap get with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashMapGetKeyDoesNotExist() {
        MyHashMap<Integer, String> map = new MyHashMap<>();

        map.put(1, "Apple");
        map.put(2, "Banana");

        assertNull(map.get(3));
        assertEquals(2, map.size());
        assertEquals("Apple", map.get(1));
        assertEquals("Banana", map.get(2));
    }

    /**
     * Test MyHashMap put with a null key
     */
    @Test
    public void testMyHashMapPutNullKey() {
        MyHashMap<Integer, String> map = new MyHashMap<>();

        try {
            map.put(null, "Apple");
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
        }

        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * Test MyHashMap put with a key that already exists in the hash table
     */
    @Test
    public void testMyHashMapPutKeyAlreadyExists() {
        MyHashMap<Integer, String> map = new MyHashMap<>();

        assertNull(map.put(1, "Apple"));
        assertEquals("Apple", map.put(1, "Banana"));

        assertEquals("Banana", map.get(1));
        assertEquals(1, map.size());

        assertEquals("Banana", map.put(1, "Cherry"));
        assertEquals("Cherry", map.get(1));
        assertEquals(1, map.size());
    }

    /**
     * Test MyHashMap remove with a null key
     */
    @Test
    public void testMyHashMapRemoveNullKey() {
        MyHashMap<Integer, String> map = new MyHashMap<>();

        map.put(1, "Apple");

        try {
            map.remove(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
        }

        assertEquals(1, map.size());
        assertEquals("Apple", map.get(1));
    }

    /**
     * Test MyHashMap remove with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashMapRemoveKeyDoesNotExist() {
        MyHashMap<Integer, String> map = new MyHashMap<>();

        map.put(1, "Apple");
        map.put(2, "Banana");

        assertNull(map.remove(3));

        assertEquals(2, map.size());
        assertEquals("Apple", map.get(1));
        assertEquals("Banana", map.get(2));
    }

    /**
     * Test MyHashMap getHash with a null key
     */
    @Test
    public void testMyHashMapGetHashNullKey() {
        MyHashMap<Integer, String> map = new MyHashMap<>();

        try {
            map.getHash(null, 5);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
        }
    }

    // ----------------MyHashSet class----------------

    /**
     * Test MyHashMap put with a key that already exists in the hash table
     */
    @Test
    public void testMyHashSetAddAlreadyExists() {
        MyHashSet<Integer> set = new MyHashSet<>();

        assertTrue(set.add(10));
        assertFalse(set.add(10));

        assertEquals(1, set.size());
        assertTrue(set.contains(10));

        assertTrue(set.add(20));
        assertFalse(set.add(20));

        assertEquals(2, set.size());
        assertTrue(set.contains(20));
    }

    /**
     * Test MyHashSet remove with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashSetRemoveDoesNotExist() {
        MyHashSet<Integer> set = new MyHashSet<>();

        set.add(10);
        set.add(20);

        assertFalse(set.remove(30));

        assertEquals(2, set.size());
        assertTrue(set.contains(10));
        assertTrue(set.contains(20));

        assertTrue(set.remove(10));
        assertFalse(set.remove(10));
        assertEquals(1, set.size());
    }
}
