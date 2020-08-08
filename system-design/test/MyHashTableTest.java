import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MyHashTableTest {

    MyHashTable ht;

    @BeforeEach
    public void setUp() {
        ht = new MyHashTable();
    }

    @Test
    public void testSet() {
        ht.set("key1", 10);
        assertEquals(10, ht.get("key1"));
    }

    @Test
    public void testDuplicateSet() {
        ht.set("key2", 20);
        ht.set("key2", 30);
        assertEquals(30, ht.get("key2"));
    }

    @Test
    public void testRemove() {
        ht.set("key3", 40);
        ht.set("key4", 50);
        ht.remove("key3");
        assertEquals(null, ht.get("key3"));
    }

    @Test
    public void checkInitVal() {
        int[] array = new int[3];
        assertEquals(0, array[0]);
        assertEquals(0, array[1]);
        assertEquals(0, array[2]);

    }

//    @Test
//    public void testHash() {
//        String key = "key1";
//        char[] chars = key.toCharArray();
//        int hashed = 0;
//        for(char c: chars) {
//            hashed += c;
//        }
//        int salt = 2453252;
//        hashed = (hashed + salt) % (100000);
//        assertEquals(hashed, MyHashTable.hash(key));
//    }
//    @Test
//    public void checkLibHashSet() {
//        Map<String, Integer> m = new HashMap<>();
//        m.put("key1", 10);
//        m.put("key1", 20);
//        System.out.println(m);
//    }
}