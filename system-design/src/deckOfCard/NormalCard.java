package deckOfCard;

public class NormalCard extends Card implements Comparable<NormalCard>{
    private int number;

    public NormalCard(Mark mark, int number) {
        super(mark);
        if (mark.equals(Mark.Joker)) {
            throw new IllegalArgumentException("Normal card can't have Joker mark.");
        }
        this.number = number;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Card other) {
        return this.mark == other.mark && this.number == other.getNumber();

    }

    @Override
    public String toString() {
        return "Mark: " + this.mark + ", Number: "+ this.number;
    }

    /**
     * reverse order
     * Cards are set by descending order
     * @param other
     * @return
     */
    @Override
    public int compareTo(NormalCard other) {
        int markCompare = this.mark.ordinal() - other.mark.ordinal();
        return 10 * (other.number - this.number) + markCompare;
    }
}
