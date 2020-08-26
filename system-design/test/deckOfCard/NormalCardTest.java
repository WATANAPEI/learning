package deckOfCard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalCardTest {

    @Test
    public void testNormalCard() {
        NormalCard n = new NormalCard(Mark.Spade, 3);
        assertEquals(3, n.getNumber());
        assertEquals(Mark.Spade, n.getMark());
    }

}