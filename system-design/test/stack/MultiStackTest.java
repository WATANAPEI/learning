package stack;

import jdk.jfr.StackTrace;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiStackTest {
    @Test
    public void testMultiStack() {
        MultiStack m = new MultiStack(3, 5);
        m.push(1, 5);
        assertEquals(1, m.getSize(1));
        m.push(1, 2);
        assertEquals(2, m.pop(1));
        assertEquals(1, m.getSize(1));
        assertEquals(5, m.peek(1));
    }

    @Test
    public void testEmptyPop() {
        MultiStack m = new MultiStack(1, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            m.pop(2);
        });
    }

    @Test
    public void testFullPush() {
        MultiStack m = new MultiStack(1, 1);
        m.push(1, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            m.push(1, 2);
        });
    }

}