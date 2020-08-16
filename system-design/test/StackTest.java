import stack.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    Stack s;

    @BeforeEach
    public void setUp() {
        s = new Stack();
    }

    @Test
    public void testStack() {
        s.push(10);
        s.push(20);
        assertEquals(20, s.pop());
        assertEquals(1, s.size());
        assertEquals(10, s.peek());
        assertEquals(1, s.size());
    }

    @Test
    public void testSize() {
        assertEquals(0, s.size());

    }

    @Test
    public void testCapacity() {
        s = new Stack(1);
        s.push(2);
        assertThrows(IllegalStateException.class, () -> {
            s.push(3);
        });

    }

    @Test
    public void testIsEmpty() {
        s = new Stack();
        assertEquals(true, s.isEmpty());
    }

}