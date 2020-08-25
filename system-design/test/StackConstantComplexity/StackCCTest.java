package StackConstantComplexity;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StackCCTest {

    @Test
    public void testPushAndPop() {
        StackCC s = new StackCC();
        s.push(3);
        s.push(5);
        s.push(2);
        assertEquals(2, s.peekMin());

    }

    @Test
    public void testEmptyPop() {
        StackCC s = new StackCC();
        assertThrows(NoSuchElementException.class, ()-> {
            s.pop();
        });
    }

}