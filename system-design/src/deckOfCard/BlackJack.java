package deckOfCard;

import java.util.*;

public class BlackJack {
    Deck deck;
    List<Participant> participantList;
    public BlackJack() {
        deck = new Deck();
        participantList = new ArrayList<>();
    }

    public void participate(Participant p) {
        if(participantList.contains(p)) {
            throw new IllegalStateException("Already exists participant " + p.getName());
        }
        if(p == null) {
            throw new IllegalArgumentException("Participant is null.");
        }
        participantList.add(p);
    }

    public void prepare() {
        deck.shuffle();

    }

    public void deal() {
        int dealTimes = 2;

        if(participantList.isEmpty()) {
            throw new IllegalStateException("No participants in this game.");
        }

        for(int i = 0; i < dealTimes; i++) {
            for(Participant p: participantList) {
                Card c = deck.draw();
                p.haveCard(c);
            }
        }
    }
}

class Participant {
    private UUID id;
    private Set<Card> hand;
    private String name;

    public Participant() {
        this.name = "Anonymous";
        this.id = UUID.randomUUID();
        this.hand = new HashSet<>();
    }

    public Participant(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.hand = new HashSet<>();
    }

    public String getName() {
        return this.name;
    }

    public UUID getId() {
        return this.id;
    }

    // TODO: make this method public only for BlackJack class
    void haveCard(Card c) {
        this.hand.add(c);
    }

    Set<Card> getHand() {
        return this.hand;
    }
}
