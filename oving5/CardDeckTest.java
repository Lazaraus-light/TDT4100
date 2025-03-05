package oving5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

	private Card s1;
	private Card h1;
	private Card d1;
	private Card c1;
	private Card s2;
	private Card h2;
	private Card d2;
	private Card c2;
	private CardDeck deck;
	private Collection<Card> expected;

	private static void testCards(Iterable<Card> actual, Iterator<Card> expected) {
		Iterator<Card> actualIt = actual.iterator();

		while (expected.hasNext()) {
			assertTrue(actualIt.hasNext());

			Card expectedCard = expected.next();
			Card actualCard = actualIt.next();
			assertEquals(expectedCard.getSuit(), actualCard.getSuit(),
					String.format("Kortet skulle vært %s men var %s ", expectedCard, actualCard));
			assertEquals(expectedCard.getFace(), actualCard.getFace(),
					String.format("Kortet skulle vært %s men var %s", expectedCard, actualCard));
		}
	}

	private static void testCards(CardContainer it, Collection<Card> expected) {
		assertEquals(expected.size(), it.getCardCount());

		Iterator<Card> expectedIt = expected.iterator();
		int i = 0;

		while (expectedIt.hasNext()) {
			Card expectedCard = expectedIt.next();
			Card actualCard = it.getCard(i);
			assertEquals(expectedCard.getSuit(), actualCard.getSuit(), String.format(
					"Kort nummer %d skulle vært %s men var %s ", i + 1, expectedCard, actualCard));
			assertEquals(expectedCard.getFace(), actualCard.getFace(), String.format(
					"Kort nummer %d skulle vært %s men var %s ", i + 1, expectedCard, actualCard));

			i++;
		}
	}

	@BeforeEach
	public void setUp() {
		deck = new CardDeck(2);
		s1 = new Card('S', 1);
		s2 = new Card('S', 2);
		h1 = new Card('H', 1);
		h2 = new Card('H', 2);
		d1 = new Card('D', 1);
		d2 = new Card('D', 2);
		c1 = new Card('C', 1);
		c2 = new Card('C', 2);
		expected = new ArrayList<>(List.of(s1, s2, h1, h2, d1, d2, c1, c2));
	}

	@Test
	@DisplayName("Sjekker at CardContainer fungerer med CardDeck")
	public void testCardContainer() {
		CardDeckTest.testCards(deck, expected);
	}

	@Test
	@DisplayName("Sjekker at iterator fungerer med CardDeck")
	public void testDeckIterator() {
		CardDeckTest.testCards(deck, expected.iterator());
	}
}
