package deckOfCard;

public abstract class Card {
    Mark mark;
    public Card(Mark mark) {
        this.mark = mark;
    }

    public Mark getMark() {
        return this.mark;
    }
}
