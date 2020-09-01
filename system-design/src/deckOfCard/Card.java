package deckOfCard;

public abstract class Card {
    protected Mark mark;
    public Card(Mark mark) {
        this.mark = mark;
    }

    public Mark getMark() {
        return this.mark;
    }

    abstract public int getNumber();
    abstract public boolean equals(Card other);
    abstract public String toString();
}
