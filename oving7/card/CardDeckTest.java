package oving7.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

	private Card s1;
	private Card s2;
	private Card h1;
	private Card h2;
	private Card d1;
	private Card d2;
	private Card c1;
	private Card c2;
	private CardDeck deck;
	private List<Card> expected;

	private static void testCards(CardContainer it, List<Card> expected) {
		assertEquals(expected.size(), it.getCardCount());

		for (int i = 0; i < expected.size(); i++) {
			Card expectedCard = expected.get(i);
			Card actualCard = it.getCard(i);
			assertEquals(expectedCard.getSuit(), actualCard.getSuit(), String.format(
					"Kort nummer %d skulle vært %s men var %s ", i + 1, expectedCard, actualCard));
			assertEquals(expectedCard.getFace(), actualCard.getFace(), String.format(
					"Kort nummer %d skulle vært %s men var %s ", i + 1, expectedCard, actualCard));

			i++;
		}
	}

	private static void testCards(Iterator<Card> actual, Iterator<Card> expected) {
		while (expected.hasNext()) {
			assertTrue(actual.hasNext());

			Card expectedCard = expected.next();
			Card actualCard = actual.next();
			assertEquals(expectedCard.getSuit(), actualCard.getSuit(),
					String.format("Kortet skulle vært %s men var %s ", expectedCard, actualCard));
			assertEquals(expectedCard.getFace(), actualCard.getFace(),
					String.format("Kortet skulle vært %s men var %s", expectedCard, actualCard));
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
		expected = new LinkedList<>(List.of(s1, s2, h1, h2, d1, d2, c1, c2));
	}

	@Test
	@DisplayName("Test maxCardCount")
	public void testMaxCardCount() {
		assertTrue(deck instanceof CardContainerImpl);
		assertEquals(52, deck.getMaxCardCount());
	}

	@Test
	@DisplayName("Test at cardDeckImpl implementerer cardContainer")
	public void testCardContainer() {
		CardDeckTest.testCards(deck, expected);
	}

	@Test
	@DisplayName("Test at cardDeckImpl implementerer iterable")
	public void testDeckIterator() {
		CardDeckTest.testCards(deck.iterator(), expected.iterator());
	}
}
