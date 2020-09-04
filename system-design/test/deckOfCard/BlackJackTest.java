package deckOfCard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlackJackTest {
    private Deck deck;
    List<Participant> participantList;

    @BeforeEach
    public void setUp() {
        deck = spy(Deck.class);
        participantList = new ArrayList();
        Participant p1 = new Participant("Alice");
        participantList.add(p1);
        Participant p2 = new Participant("Bob");
        participantList.add(p2);
    }

    @Test
    public void testMockResponse() {
        when(deck.draw())
                .thenReturn(new NormalCard(Mark.Heart, 3))
                .thenReturn(new NormalCard(Mark.Spade, 4))
                .thenReturn(new NormalCard(Mark.Spade, 5))
                .thenReturn(new NormalCard(Mark.Clover, 6));
        assertEquals(Mark.Heart, deck.draw().getMark());
        assertEquals(4, deck.draw().getNumber());
        assertEquals(Mark.Spade, deck.draw().getMark());
        assertEquals(6, deck.draw().getNumber());
    }

    @Test
    public void testScoreUnder21() {
        when(deck.draw())
                .thenReturn(new NormalCard(Mark.Heart, 3))
                .thenReturn(new NormalCard(Mark.Spade, 4))
                .thenReturn(new NormalCard(Mark.Spade, 5))
                .thenReturn(new NormalCard(Mark.Clover, 6));


        BlackJack bj = new BlackJack(deck, participantList);
        bj.prepare();
        bj.deal();
        assertEquals(3 + 5, bj.score(bj.participantList.get(0)));
        assertEquals(4 + 6, bj.score(bj.participantList.get(1)));
    }

    @Test
    public void testScoreOver21() {
        when(deck.draw())
                .thenReturn(new NormalCard(Mark.Heart, 12))
                .thenReturn(new NormalCard(Mark.Spade, 10))
                .thenReturn(new NormalCard(Mark.Spade, 10))
                .thenReturn(new NormalCard(Mark.Clover, 11));

        BlackJack bj = new BlackJack(deck, participantList);
        bj.prepare();
        bj.deal();
        assertEquals(10 + 10, bj.score(bj.participantList.get(0))); // 12 -> 10
        assertEquals(10 + 10, bj.score(bj.participantList.get(1))); // 11 -> 10
    }

    @Test
    public void testDraw3Times() {
        when(deck.draw())
                .thenReturn(new NormalCard(Mark.Heart, 3))
                .thenReturn(new NormalCard(Mark.Spade, 8))
                .thenReturn(new NormalCard(Mark.Spade, 4))
                .thenReturn(new NormalCard(Mark.Clover, 10))
                .thenReturn(new NormalCard(Mark.Clover, 10));

        BlackJack bj = new BlackJack(deck, participantList);
        bj.prepare();
        bj.deal();
        bj.hit(participantList.get(0));
        assertEquals(3 + 4 + 10, bj.score(bj.participantList.get(0)));
        assertEquals(8 + 10, bj.score(bj.participantList.get(1)));
    }

    @Test
    public void testWithAce() {
        when(deck.draw())
                .thenReturn(new NormalCard(Mark.Heart, 7))
                .thenReturn(new NormalCard(Mark.Spade, 8))
                .thenReturn(new NormalCard(Mark.Spade, 1))
                .thenReturn(new NormalCard(Mark.Clover, 10));

        BlackJack bj = new BlackJack(deck, participantList);
        bj.prepare();
        bj.deal();
        assertEquals(7 + 11, bj.score(bj.participantList.get(0)));
        assertEquals(8 + 10, bj.score(bj.participantList.get(1)));
    }


}