package queueBy2Stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueByStacksTest {
    @Test
    public void testEmptyQueue() {
        QueueByStacks q = new QueueByStacks();
        assertThrows(IllegalStateException.class, () -> {
            q.deque();
        });
        q.enque(3);
        assertEquals(3, q.deque());
    }

    @Test
    public void testEnque() {
        QueueByStacks q = new QueueByStacks();
        q.enque(3);
        q.enque(5);
        assertEquals(3, q.deque());

    }


}