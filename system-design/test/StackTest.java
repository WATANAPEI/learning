import Stack.Stack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    public void testStack() {
        Stack s = new Stack();
        s.push(10);
        s.push(20);
        assertEquals(20, s.pop());
        assertEquals(1, s.size());
        assertEquals(10, s.peek());
        assertEquals(1, s.size());

    }

}