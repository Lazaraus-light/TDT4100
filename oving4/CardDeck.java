package oving4;

import java.util.ArrayList;
import java.util.List;


public class CardDeck {
    private final List<Card> cards;


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


    public int getCardCount() {
        return cards.size();
    }


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
}
