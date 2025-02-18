package oving3;


public class Card {
    private final char suit;
    private final int face;

    public Card(char suit, int face) {
        if (suit != 'S' && suit != 'H' && suit != 'D' && suit != 'C') {
            throw new IllegalArgumentException("Invalid suit: " + suit);
        }
        if (face < 1 || face > 13) {
            throw new IllegalArgumentException("Invalid face value: " + face);
        }
        this.suit = suit;
        this.face = face;
    }

    public char getSuit() {
        return this.suit;
    }

    public int getFace() {
        return this.face;
    }

    @Override
    public String toString() {
        return "" + suit + face;
    }

    public static void main(String[] args) {
        Card card = new Card('S', 1);
        System.out.println(card);
    }
}
