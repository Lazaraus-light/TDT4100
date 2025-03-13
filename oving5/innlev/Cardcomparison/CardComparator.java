package oving5.Card;

package oving5.Card;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

    private final boolean aceHigh;
    private final char trumpSuit;

    public CardComparator(boolean aceHigh, char trumpSuit) {
        this.aceHigh = aceHigh;
        
        if (trumpSuit == 'S' || trumpSuit == 'H' || trumpSuit == 'D' || trumpSuit == 'C') {
            this.trumpSuit = trumpSuit;
        } else {
            this.trumpSuit = ' '; 
        }
    }

    @Override
    public int compare(Card c1, Card c2) {
        
        boolean c1IsTrump = (c1.suit == trumpSuit);
        boolean c2IsTrump = (c2.suit == trumpSuit);
        if (c1IsTrump && !c2IsTrump) {
            return 1;  
        } else if (!c1IsTrump && c2IsTrump) {
            return -1; 
        }
       
        if (!c1IsTrump && !c2IsTrump) {
            int suitComparison = Integer.compare(getDefaultSuitRank(c1.suit), getDefaultSuitRank(c2.suit));
            if (suitComparison != 0) {
                return suitComparison;
            }
        }
        
        int c1Value = adjustedFaceValue(c1.face);
        int c2Value = adjustedFaceValue(c2.face);
        return Integer.compare(c1Value, c2Value);
    }

    private int adjustedFaceValue(int face) {
        
        if (aceHigh && face == 1) {
            return 14;
        }
        return face;
    }

    private int getDefaultSuitRank(char suit) {
        
        switch (suit) {
            case 'C': return 1;
            case 'D': return 2;
            case 'H': return 3;
            case 'S': return 4;
            default: throw new IllegalArgumentException("Invalid suit: " + suit);
        }
    }
}
