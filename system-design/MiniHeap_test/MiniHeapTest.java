import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiniHeapTest {

    MiniHeap m;
    @BeforeEach
    public void setUp() {
        m = new MiniHeap();
    }

    @Test
    public void testMiniHeap() {
        m.insert(10);
        m.insert(15);
        m.insert(2);
        assertEquals(2, m.extractMin());
    }

    @Test
    public void testPeek() {
        m.insert(3);
        m.insert(20);
        assertEquals(3, m.top());
    }

    @Test
    public void testUpMin() {
        m.insert(3);
        m.insert(5);
        m.insert(10);
        m.insert(6);
        m.insert(2);
        m.upMin(4);
        assertEquals(2, m.top());

    }

    @Test
    public void testOneElement() {
        m.insert(3);
        assertEquals(3, m.extractMin());
    }

}