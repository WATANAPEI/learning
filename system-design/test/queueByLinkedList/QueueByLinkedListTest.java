package queueByLinkedList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueueByLinkedListTest {
    @Test
    public void testAppend() {
        MyLinkedList ll = new MyLinkedList();
        ll.append(3);
        assertEquals(3, ll.get(0));
        ll.append(4);
        assertEquals(4, ll.get(1));
    }

    @Test
    public void testInsert() {
        MyLinkedList ll = new MyLinkedList();
        ll.append(3);
        ll.append(2);
        ll.append(6);
        ll.insert(1, 1);
        assertEquals(4, ll.size());
        assertEquals(1, ll.get(1));
        ll.insert(0, 10);
        assertEquals(10, ll.get(0));
    }

}