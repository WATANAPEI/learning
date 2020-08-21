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
        assertThrows(IllegalStateException.class, () -> {
            s.pop();
        });
        s.push(3);
        assertEquals(3, s.pop());
        s.push(4);
        s.push(5);
        assertEquals(5, s.pop());

    }

    @Test
    public void testStackMany() {
        SetOfStack s = new SetOfStack();
        for(int i = 0; i < 20; i++) {
            s.push(i);
        }
        assertEquals(19, s.pop());
        assertEquals(2, s.getStackNumber());
    }

}