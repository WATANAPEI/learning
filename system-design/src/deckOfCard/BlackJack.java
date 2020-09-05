package deckOfCard;

import java.util.*;

public class BlackJack {
    Deck deck;
    List<Player> playerList;
    public BlackJack(Deck deck, List<Player> players) {
        this.deck = deck;
        this.playerList = players;
    }

    public void participate(Player p) {
        if(playerList.contains(p)) {
            throw new IllegalStateException("Already exists participant " + p.getName());
        }
        if(p == null) {
            throw new IllegalArgumentException("Participant is null.");
        }
        playerList.add(p);
    }

    public void prepare() {
        deck.shuffle();
    }

    public void hit(Player p) {
        Card c = deck.draw();
        p.haveCard(c);
    }


    public int score(Player p) {
        if(!playerList.contains(p)) {
            throw new IllegalStateException("Participant " + p.getName() + " is already member.");
        }
        if(p == null) {
            throw new IllegalArgumentException("Participant is null");
        }
        TreeSet<Card> treeSet = p.getHand();
        ScoreTree scoreTree = new ScoreTree(treeSet);

        return scoreTree.getBestScore();

    }

    public void deal() {
        int dealTimes = 2;

        if(playerList.isEmpty()) {
            throw new IllegalStateException("No participants in this game.");
        }

        for(int i = 0; i < dealTimes; i++) {
            for(Player p: playerList) {
                Card c = deck.draw();
                p.haveCard(c);
            }
        }
    }
}

class Player {
    private UUID id;
    private TreeSet<Card> hand;
    private String name;

    public Player() {
        this.name = "Anonymous";
        this.id = UUID.randomUUID();
        this.hand = new TreeSet<>();
    }

    public Player(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.hand = new TreeSet();
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

    TreeSet<Card> getHand() {
        return this.hand;
    }


}

