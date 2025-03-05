package oving6.observable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StockIndexTest {

	private static final double applePrice = 534.98;
	private static final double facebookPrice = 67.80;
	private static final double epsilon = 0.000001;

	private Stock apple;
	private Stock facebook;
	private StockIndex index0;
	private StockIndex index1;
	private StockIndex indexN;

	@BeforeEach
	public void setUp() {
		apple = new Stock("AAPL", applePrice);
		facebook = new Stock("FB", facebookPrice);

		index0 = new StockIndex("OSEBX");
		index1 = new StockIndex("OSEBX", facebook);
		indexN = new StockIndex("OSEBX", facebook, apple);
	}

	@Test
	@DisplayName("Teste konstruktør")
	public void testConstructor() {
		assertEquals(0.0, index0.getIndex(), epsilon, "Teste verdien til indeks med 0 aksjer ");
		assertEquals(facebookPrice, index1.getIndex(), epsilon,
				"Teste verdien til indeks med 1 aksje");
		assertEquals(facebookPrice + applePrice, indexN.getIndex(), epsilon,
				"Teste verdien til indeks med 2 aksjer");

		assertThrows(IllegalArgumentException.class, () -> {
			new StockIndex(null);
		}, "Teste konstruktør med null navn");

		assertThrows(IllegalArgumentException.class, () -> {
			new StockIndex("OSEBX", apple, null, facebook);
		}, "Teste konstruktør med null aksjer");
	}

	@Test
	@DisplayName("Legge til aksje")
	public void testAddStock() {
		assertEquals(0.0, index0.getIndex(), epsilon, "Teste verdien til indeks med 0 aksjer");

		index0.addStock(facebook);
		assertEquals(facebookPrice, index0.getIndex(), epsilon,
				"Teste verdien til indeks etter å ha lagt til 1 aksje");

		assertThrows(IllegalArgumentException.class, () -> {
			index0.addStock(null);
		}, "Teste legge til null aksje");
	}

	@Test
	@DisplayName("Legge til samme aksje to ganger")
	public void testAddDuplicateStocks() {
		assertEquals(0.0, index0.getIndex(), epsilon, "Teste verdien til indeks med 0 aksjer");

		index0.addStock(facebook);
		assertEquals(facebookPrice, index0.getIndex(), epsilon,
				"Teste verdien til indeks etter å ha lagt til 1 aksje");

		index0.addStock(facebook);
		assertEquals(facebookPrice, index0.getIndex(), epsilon,
				"Teste verdien til indeks etter å ha lagt til aksje som allerede er med i indeks");
	}

	@Test
	@DisplayName("Fjerne aksje")
	public void testRemoveStock() {
		assertEquals(facebookPrice + applePrice, indexN.getIndex(), epsilon,
				"Teste verdien til indeks med 2 aksjer");

		indexN.removeStock(apple);
		assertEquals(facebookPrice, indexN.getIndex(), epsilon,
				"Teste verdien til indeks etter å ha fjernet 1 aksje");

		indexN.removeStock(apple);
		assertEquals(facebookPrice, indexN.getIndex(), epsilon,
				"Teste verdien til indeks etter å ha fjernet 1 aksje som ikke var med i indeks");

		indexN.removeStock(facebook);
		assertEquals(0.0, indexN.getIndex(), epsilon,
				"Teste verdien til indeks etter å ha fjernet eneste aksje i indeks");
	}

	@Test
	@DisplayName("Endre aksjepris")
	public void testChangePrice() {
		double facebookPrice2 = 67.0;
		double facebookPrice3 = 69.0;

		facebook.setPrice(facebookPrice2);
		assertEquals(facebookPrice2, index1.getIndex(), epsilon,
				"Teste verdien til indeks med 1 aksje etter å ha endret prisen på aksje");
		assertEquals(facebookPrice2 + applePrice, indexN.getIndex(), epsilon,
				"Teste verdien til indeks med 2 aksjer etter å ha endret prisen til 1 av aksjene");

		facebook.setPrice(facebookPrice3);
		assertEquals(facebookPrice3, index1.getIndex(), epsilon,
				"Teste verdien til indeks med 1 aksje etter å ha endret prisen på aksje for andre gang");
		assertEquals(facebookPrice3 + applePrice, indexN.getIndex(), epsilon,
				"Teste verdien til indeks med 2 aksjer etter å ha endret prisen til 1 av aksjene for andre gang");
	}
}
