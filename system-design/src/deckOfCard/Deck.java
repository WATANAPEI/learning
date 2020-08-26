package deckOfCard;

import java.util.*;

public class Deck {
    private LinkedList<Card> cards;

    public Deck() {
        cards = new LinkedList<>();
        List<Mark> markList = new ArrayList();
        markList.add(Mark.Spade);
        markList.add(Mark.Clover);
        markList.add(Mark.Diamond);
        markList.add(Mark.Heart);
        for(Mark m: markList) {
            for(int i = 1; i <= 13; i++) {
                cards.add(new NormalCard(m, i));
            }
        }
        cards.add(new JokerCard());
    }

    public Card draw() {
        Card c =  cards.getLast();
        cards.removeLast();
        return c;
    }

    public int count() {
        return cards.size();
    }
}
