package deckOfCard;

import java.util.*;

public class Deck {
    private LinkedList<Card> cards;

    public Deck() {
        cards = new LinkedList();
        Mark[] markList = {Mark.Spade, Mark.Clover, Mark.Diamond, Mark.Joker.Heart};
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

    public Card peek() {
        return cards.getLast();
    }

    public int count() {
        return cards.size();
    }

    public void shuffle() {
        MTRandom m = new MTRandom();
        LinkedList<Card> _tmp = new LinkedList();
        int bitNum = 6;
        int cardNum = cards.size();
        boolean[] used = new boolean[cardNum];
        for(int i = 0; i < cardNum; i++) {
            int rand = m.next(bitNum);
            while(rand >= cardNum || used[rand] == true) {
                rand = m.next(bitNum);
            }
            _tmp.add(cards.get(rand));
            used[rand] = true;

        }
        cards = _tmp;

    }

}
