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

    @Test
    public void testShuffle() {
        Deck d = new Deck();
        d.shuffle();
        Card[] cards1 = new Card[53];
        for(int i = 0; i < 53; i++) {
            cards1[i] = d.draw();
        }
        Deck d2 = new Deck();
        d2.shuffle();
        Card[] cards2 = new Card[53];
        for(int j = 0; j < 53; j++) {
            cards2[j] = d2.draw();
        }
    }

    @Test
    public void checkMT19937() {
        MTRandom m = new MTRandom();
        //int[] seeds = {1, 2, 3, 4, 5};
        //m.setSeed(seeds);
        System.out.println(m.next(32));
        System.out.println(m.next(32));
        System.out.println(m.next(32));
        System.out.println(m.next(32));
        System.out.println(m.next(32));
        System.out.println(m.next(32));
    }

}