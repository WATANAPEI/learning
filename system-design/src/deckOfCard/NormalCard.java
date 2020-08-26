package deckOfCard;

public class NormalCard extends Card {
    int number;

    public NormalCard(Mark mark, int number) {
        super(mark);
        if (mark.equals(Mark.Joker)) {
            throw new IllegalArgumentException("Normal card can't have Joker mark.");
        }
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

}
