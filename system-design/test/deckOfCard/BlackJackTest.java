package deckOfCard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BlackJackTest {
    @Test
    public void testScore() {
        Deck deck = new Deck();
        List<Participant> participantList = new ArrayList();

        BlackJack bj = new BlackJack(deck, participantList);
        bj.prepare();
        Participant p1 = new Participant("Alice");
        bj.participate(p1);
        bj.deal();
        assertEquals(1, bj.score(p1));
    }

    @Test
    public void testBlackJackStart() {
        Deck deck = new Deck();
        List<Participant> participantList = new ArrayList();
        BlackJack bj = new BlackJack(deck, participantList);
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
        int p1Score = bj.score(p1);
        int p2Scpre = bj.score(p1);

    }

}