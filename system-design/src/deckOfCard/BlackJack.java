package deckOfCard;

import java.util.*;

public class BlackJack {
    Deck deck;
    List<Participant> participantList;
    public BlackJack(Deck deck, List<Participant> participants) {
        this.deck = deck;
        this.participantList = participants;
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

    public int score(Participant p) {
        if(!participantList.contains(p)) {
            throw new IllegalStateException("Participant " + p.getName() + " is already member.");
        }
        if(p == null) {
            throw new IllegalArgumentException("Participant is null");
        }

        return p.score();
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

    public int score() {
        int result = 0;
        for(Card c : hand) {
            if(c.getNumber() >= 10) {
                result += 10;
            }else {
                result += c.getNumber();
            }
        }
        if(result > 21) {
            result = 0;
        }
        return result;
    }
}
