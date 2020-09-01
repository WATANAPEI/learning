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
        Deck d2 = new Deck();
        d2.shuffle();
        int testTimes = 10;
        int equalTime = 0;
        for(int i = 0; i < testTimes; i++) {
            if(d.draw().equals(d2.draw())) {
                equalTime++;
            }
        }
        assertTrue(equalTime < 3); // Same card can appear in the same order three times at best
    }

    @Test
    public void checkMT19937() {
        MTRandom m = new MTRandom();
        int bitNum = 6;
        int cardNum = 53;
        //int[] seeds = {1, 2, 3, 4, 5};
        //m.setSeed(seeds);
        boolean[] used = new boolean[cardNum];
        for(int i = 0; i < cardNum; i++) {
            int rand = m.next(bitNum);
            while(rand >= cardNum || used[rand] == true) {
                rand = m.next(bitNum);
            }
            System.out.println(rand);
            used[rand] = true;

        }
    }

}