package stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StackByLinkedListTest {
    StackByLinkedList s;

    @BeforeEach
    public void setUp() {
        s = new StackByLinkedList();
    }

    @Test
    public void testEmptyStackPop() {
        assertThrows(NoSuchElementException.class, () -> {
            s.pop();
        });
    }

    @Test
    public void testPushAndPop() {
        s.push(3);
        s.push(5);
        assertEquals(5, s.pop());
    }

    @Test
    public void testPeek() {
        s.push(4);
        s.push(2);
        assertEquals(2, s.peek());

    }

    @Test
    public void testIsEmpty() {
        assertEquals(true, s.is_empty());
        s.push(2);
        assertEquals(false, s.is_empty());
    }

}