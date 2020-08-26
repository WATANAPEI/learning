package deckOfCard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JokerCardTest {

    @Test
    public void testJokerCard() {
        Card card = new JokerCard();
        assertEquals(Mark.Joker, card.getMark());
    }

}