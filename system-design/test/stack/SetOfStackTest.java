package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetOfStackTest {

    @Test
    public void testStackCreate() {
        SetOfStack s = new SetOfStack();
        assertEquals(2, s.getStackNumber());
        SetOfStack s2 = new SetOfStack(3, 3);
        assertEquals(3, s2.getStackNumber());
    }

    @Test
    public void testStackPush() {
        SetOfStack s = new SetOfStack();
        s.push(3);
        assertEquals(3, s.pop());
        s.push(4);
        s.push(5);
        assertEquals(5, s.pop());

    }

}