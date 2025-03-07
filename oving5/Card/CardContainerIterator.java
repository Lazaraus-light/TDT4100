package oving5.Card;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CardContainerIterator implements Iterator<Card> {
    private final CardContainer cardContainer;
    private int currentIndex = 0;

    public CardContainerIterator(CardContainer cardContainer) {
        if (cardContainer == null) {
            throw new IllegalArgumentException("CardContainer cannot be null");
        }
        this.cardContainer = cardContainer;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < cardContainer.getCardCount();
    }

    @Override
    public Card next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more cards");
        }
        return cardContainer.getCard(currentIndex++);
    }
}
