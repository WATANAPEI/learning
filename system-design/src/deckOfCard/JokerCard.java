package deckOfCard;

public class JokerCard extends Card {
    public JokerCard() {
        super(Mark.Joker);
    }

    @Override
    public Mark getMark() {
        return Mark.Joker;
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public boolean equals(Card other) {
        return this.mark == other.mark;
    }

    @Override
    public String toString() {
        return "Mark: " + this.mark;
    }
}
