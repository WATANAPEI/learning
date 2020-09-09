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

    @Test
    public void testDelete() {
        MyLinkedList ll = new MyLinkedList();
        ll.append(3);
        ll.append(4);
        ll.append(5);
        ll.delete(1);
        assertEquals(2, ll.size());
        ll.append(1);
        ll.delete(0);
        assertEquals(2, ll.size());
        ll.delete(1);
        assertEquals(5, ll.get(0));
    }

    @Test
    public void testEnqueueDequeue() {
        QueueByLinkedList q = new QueueByLinkedList();
        q.enqueue(3);
        q.enqueue(4);
        assertEquals(2, q.size());
        assertEquals(3, q.dequeue());
        assertEquals(4, q.dequeue());

    }

    @Test
    public void testFindStart() {
        MyLinkedList<Integer> l = new MyLinkedList();
        l.append(3);;
        l.append(2);
        l.append(4);;
        assertEquals(4, l.last());

    }

}