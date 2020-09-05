package deckOfCard;

import com.sun.source.tree.Tree;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTreeTest {
    @Test
    public void testMakeTree() {
        Card card1 = new NormalCard(Mark.Spade, 2);
        Card card2 = new NormalCard(Mark.Heart, 4);
        Card card3 = new NormalCard(Mark.Spade, 9);
        Card card4 = new NormalCard(Mark.Clover, 1);

        TreeSet<Card> cards = new TreeSet();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        ScoreTree scoreTree = new ScoreTree(cards);
        assertEquals(4, scoreTree.depth());
    }

    @Test
    public void testMaxScoreWithoutAce() {
        Card card1 = new NormalCard(Mark.Spade, 2);
        Card card2 = new NormalCard(Mark.Heart, 4);
        Card card3 = new NormalCard(Mark.Spade, 9);

        TreeSet<Card> cards = new TreeSet();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        ScoreTree s = new ScoreTree(cards);
        assertEquals(15, s.getBestScore());

    }

    @Test
    public void testMaxScoreWithOver10Card() {
        Card card1 = new NormalCard(Mark.Spade, 2);
        Card card2 = new NormalCard(Mark.Clover, 12);
        Card card3 = new NormalCard(Mark.Heart, 2);

        TreeSet<Card> cards = new TreeSet();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        ScoreTree s = new ScoreTree(cards);
        assertEquals(14, s.getBestScore());

    }

}