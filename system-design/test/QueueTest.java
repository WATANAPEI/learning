import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    Queue pq;
    @BeforeEach
    public void setUp() {
        pq = new Queue();
    }

    @Test
    public void testQueueAndDeque() {
        pq.enq(5);
        pq.enq(4);
        int result = pq.deq();
        assertEquals(4, result);
    }

    @Test
    public void testRingBuffer() {
        for(int i = 0; i < 10; i++) {
            pq.enq(i);
        }
        assertEquals(0, pq.deq());
        pq.enq(20);
        assertEquals(1, pq.deq());
//        pq.dump();
        pq.enq(1);
        assertEquals(1, pq.deq());

//        pq.dump();
    }

    @Test
    public void testEmpty() {
        assertEquals(0, pq.deq());
    }

//    @Test
//    public void checkArrayLengthProperty()
//    {
//        Queue pq = new Queue();
//        pq.enq(3);
//        pq.enq(4);
//        pq.deq();
//        pq.dump();
//    }
}