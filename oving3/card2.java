package oving3;

/**
 * The {@code card2} class is a so-called value-based class, which is coded in such a way that its
 * objects cannot be modified after they are created. A {@code card2} object has a suit and a face.
 */
public class card2 {

    public final char suit;
    public final int face;

    /**
     * The constructor of the {@code card2} class initializes the suit and face of the card2 with the
     * first and second arguments, respectively.
     * 
     * @param suit the suit of the card2, one of {@code 'S'} (spades), {@code 'H'} (hearts),
     *        {@code 'D'} (diamonds), or {@code 'C'} (clubs)
     * @param face the face of the card2, an integer between {@code 1} (ace) and {@code 13} (king)
     *        (both inclusive)
     * @throws IllegalArgumentException if the suit or face is illegal
     * 
     * @see {@link card2Test#testConstructor}
     */


    public card2(char suit, int face) {
        // TODO: implement this constructor
        
        if (suit != 'S' && suit != 'H' && suit != 'D' && suit != 'C') {
            throw new IllegalArgumentException("Invalid suit: " + suit);
        }
        if (face < 1 || face > 13) {
            throw new IllegalArgumentException("Invalid face value: " + face);
        }
        this.suit = suit;
        this.face = face;

    }

    /**
     * @return the suit of the card2
     */
    public char getSuit() {
        // TODO: implement this method
        return this.suit;
        //return '\0';
    }

    /**
     * @return the face of the card2
     */
    public int getFace() {
        // TODO: implement this method
        return this.face;
        //return 0;
    }

    /**
     * @return the value of the card2 of the form {@code <suit><face>}. For example, the ace of
     *         spades should return {@code "S1"}
     * 
     * @see {@link card2Test#testToString}
     */
    @Override
    public String toString() {
        // TODO: implement this method
        return "" + suit + face;
        //return null;
    }

    public static void main(String[] args) {
        card2 card2 = new card2('S', 1);
        System.out.println(card2);
    }
}
