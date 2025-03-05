package oving5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardContainerIteratorTest {

	private Card c1;
	private Card c2;
	private Card d1;
	private Card d2;
	private Card h1;
	private Card h2;
	private Card s1;
	private Card s2;
	private CardContainerIterator iterator;

	private static void testCards(Iterator<Card> actual, Iterator<Card> expected) {
		while (expected.hasNext()) {
			assertTrue(actual.hasNext());

			Card actualCard = actual.next();
			Card expectedCard = expected.next();
			assertEquals(expectedCard.getSuit(), actualCard.getSuit(),
					String.format("Kortet skulle vært %s men var %s ", expectedCard, actualCard));
			assertEquals(expectedCard.getFace(), actualCard.getFace(),
					String.format("Kortet skulle vært %s men var %s", expectedCard, actualCard));
		}
	}

	@BeforeEach
	public void setUp() {
		iterator = new CardContainerIterator(new CardDeck(2));
		s1 = new Card('S', 1);
		s2 = new Card('S', 2);
		h1 = new Card('H', 1);
		h2 = new Card('H', 2);
		d1 = new Card('D', 1);
		d2 = new Card('D', 2);
		c1 = new Card('C', 1);
		c2 = new Card('C', 2);
	}

	@Test
	@DisplayName("Sjekk at iteratoren til en ny kortstokk gir ut [S1,S2,H1,H2,D1,D2,C1,C2")
	public void testConstructor() {
		CardContainerIteratorTest.testCards(iterator,
				List.of(s1, s2, h1, h2, d1, d2, c1, c2).iterator());
	}

	@Test
	@DisplayName("Sjekk at det første kortet i iteratoren er riktig")
	public void testNext() {
		Card nextCard = iterator.next();
		assertEquals(s1.toString(), nextCard.toString());
	}

	@Test
	@DisplayName("Sjekk at kortstokken inneholder minst et kort")
	public void testHasNext() {
		boolean hasNext = iterator.hasNext();
		assertTrue(hasNext);
	}
}
