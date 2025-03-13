package oving5.Card;

public class Card implements Comparable<Card> {
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

    @Override
    public int compareTo(Card other) {
        
        int thisSuitRank = getSuitRank(this.suit);
        int otherSuitRank = getSuitRank(other.suit);
        if (thisSuitRank != otherSuitRank) {
            return Integer.compare(thisSuitRank, otherSuitRank);
        }
        
        return Integer.compare(this.face, other.face);
    }

    private int getSuitRank(char suit) {
        switch (suit) {
            case 'C': return 1;
            case 'D': return 2;
            case 'H': return 3;
            case 'S': return 4;
            default: throw new IllegalArgumentException("Invalid suit: " + suit);
        }
    }
    
    public static void main(String[] args) {
        Card card = new Card('S', 1);
        System.out.println(card);
    }
}
