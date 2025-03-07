package oving5.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CardPredicate {
   
public class CardDeck implements CardContainer {
    public final List<Card> cards;

    public CardDeck(int n) {
        if (n < 0 || n > 13) {
            throw new IllegalArgumentException("Number of cards per suit must be between 0 and 13.");
        }
        this.cards = new ArrayList<>();
        char[] suits = {'S', 'H', 'D', 'C'};

        for (char suit : suits) {
            for (int face = 1; face <= n; face++) {
                cards.add(new Card(suit, face));
            }
        }
    }
    
    @Override
    public int getCardCount() {
        return cards.size();
    }
    
    @Override
    public Card getCard(int n) {
        if (n < 0 || n >= cards.size()) {
            throw new IllegalArgumentException("Invalid card index: " + n);
        }
        return cards.get(n);
    }
    
    public void shufflePerfectly() {
        List<Card> firstHalf = new ArrayList<>(cards.subList(0, cards.size() / 2));
        List<Card> secondHalf = new ArrayList<>(cards.subList(cards.size() / 2, cards.size()));
        cards.clear();

        for (int i = 0; i < firstHalf.size(); i++) {
            cards.add(firstHalf.get(i));
            if (i < secondHalf.size()) {
                cards.add(secondHalf.get(i));
            }
        }
    }
    
    @Override
    public String toString() {
        return cards.toString();
    }
    
    public void deal(CardHand hand, int i) {
        if (i > cards.size()) {
            throw new IllegalArgumentException("Not enough cards in deck to deal " + i + " cards.");
        }
        for (int j = 0; j < i; j++) {
            hand.addCard(cards.remove(0));
        }
    }
    
    // Metode som returnerer true dersom minst ett kort tilfredsstiller predicate
    public boolean hasCard(Predicate<Card> predicate) {
        if (predicate == null) {
            throw new IllegalArgumentException("Predicate cannot be null");
        }
        for (Card card : cards) {
            if (predicate.test(card)) {
                return true;
            }
        }
        return false;
    }
    
    // Metode som teller antall kort som tilfredsstiller predicate
    public int getCardCount(Predicate<Card> predicate) {
        if (predicate == null) {
            throw new IllegalArgumentException("Predicate cannot be null");
        }
        int count = 0;
        for (Card card : cards) {
            if (predicate.test(card)) {
                count++;
            }
        }
        return count;
    }
    
    // Metode som henter ut en liste med kort som tilfredsstiller predicate
    public List<Card> getCards(Predicate<Card> predicate) {
        if (predicate == null) {
            throw new IllegalArgumentException("Predicate cannot be null");
        }
        List<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (predicate.test(card)) {
                result.add(card);
            }
        }
        return result;
    }
    
    // Test-metode for de tre predicate-metodene
    public static void main(String[] args) {
        CardDeck deck = new CardDeck(13);
        System.out.println("Deck: " + deck);
        
        // Sjekk om spar dame finnes (antatt at dame har verdi 12)
        boolean hasQueenOfSpades = deck.hasCard(card -> card.getSuit() == 'S' && card.getFace() == 12);
        System.out.println("Has queen of spades: " + hasQueenOfSpades);
        
        // Tell antall hjerter (kortfarge 'H')
        int heartCount = deck.getCardCount(card -> card.getSuit() == 'H');
        System.out.println("Number of hearts: " + heartCount);
        
        // Hent ut alle ess (face 1)
        List<Card> aces = deck.getCards(card -> card.getFace() == 1);
        System.out.println("All aces: " + aces);
    }
}

}
