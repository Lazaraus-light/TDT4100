package oving5.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardHand implements CardContainer {
    public List<Card> Cards;

    public CardHand() {
        this.Cards = new ArrayList<>();
    }

    public CardHand(List<Card> Cards) {
        this.Cards = Cards;
    }

    @Override
    public int getCardCount() {
        return Cards.size();
    }

    @Override
    public Card getCard(int index) {
        if (index < 0 || index >= Cards.size()) {
            throw new IndexOutOfBoundsException("Invalid Card index");
        }
        return Cards.get(index);
    }

    public void addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null");
        }
        Cards.add(card);
    }

    public Card play(int index) {
        if (index < 0 || index >= Cards.size()) {
            throw new IndexOutOfBoundsException("Invalid Card index");
        }
        return Cards.remove(index);
    }

    @Override
    public Iterator<Card> iterator() {
        return new CardContainerIterator(this);
    }


    public Card main(int Cards) {    
        CardDeck deck = new CardDeck(13);
        System.out.println("Start kortstokk: " + deck);
        System.out.println("Antall kort i kortstokken: " + deck.getCardCount());

        CardHand hand = new CardHand();
        System.out.println("Start hånd: " + hand);
        
        deck.deal(hand, 5);
        System.out.println("\nEtter utdeling av 5 kort:");
        System.out.println("Kortstokk: " + deck);
        System.out.println("Antall kort igjen i kortstokken: " + deck.getCardCount());
        System.out.println("Korthånd: " + hand);
        System.out.println("Antall kort i hånden: " + hand.getCardCount());

        Card playedCard = hand.play(0); 
        System.out.println("\nSpilte kortet: " + playedCard);
        System.out.println("Hånd etter å ha spilt et kort: " + hand);
        System.out.println("Antall kort igjen i hånden: " + hand.getCardCount());
        return playedCard;
    }
}
