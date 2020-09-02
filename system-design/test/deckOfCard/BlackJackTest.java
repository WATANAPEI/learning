package deckOfCard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackTest {
    @Test
    public void testBlackJackStart() {
        BlackJack bj = new BlackJack();
        bj.prepare();
        Participant p1 = new Participant("Alice");
        Participant p2 = new Participant("Bob");
        bj.participate(p1);
        bj.participate(p2);
        bj.deal();
        assertNotNull(p1.getHand());
        assertNotNull(p2.getHand());
        for(Card c: p1.getHand()) {
            System.out.println(c);
        }
        for(Card c: p2.getHand()) {
            System.out.println(c);
        }

    }

}