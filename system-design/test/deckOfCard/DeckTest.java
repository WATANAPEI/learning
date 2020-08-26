package deckOfCard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    public void testDeck() {
        Deck d = new Deck();
        assertEquals(Mark.Joker, d.draw().getMark());
        assertEquals(52, d.count());
    }


}