package oving4;



public class Card {
    public final char suit;
    public final int face;

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
        Card Card = new Card('S', 1);
        System.out.println(Card);
    }
}
