package oving6.observable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StockTest {

	private Stock stock;
	private double oldPrice;
	private double newPrice;

	// Brukes for å sjekke at lyttere funker
	private double oldPriceListener;
	private double newPriceListener;

	private void setPriceForListener(double oldPrice, double newPrice) {
		oldPriceListener = oldPrice;
		newPriceListener = newPrice;
	}

	private void setPriceCheckListener(double newPrice, double expectedOldPrice,
			double expectedNewPrice) {
		// Oppdatere prisen
		this.oldPrice = this.newPrice;
		this.newPrice = newPrice;
		stock.setPrice(newPrice);

		// Sjekke at lytter har mottatt endring
		assertEquals(expectedOldPrice, this.oldPriceListener,
				"Teste gammel pris for lytter etter å ha oppdatert pris fra " + oldPrice + " til "
						+ newPrice);
		assertEquals(expectedNewPrice, this.newPriceListener,
				"Teste ny pris for lytter etter å ha oppdatert pris fra " + oldPrice + " til "
						+ newPrice);
	}

	@BeforeEach
	public void setUp() {
		stock = new Stock("APPL", 110.0);
		oldPrice = 0.0;
		newPrice = 110.0;
		oldPriceListener = 0.0;
		newPriceListener = 0.0;
	}

	@Test
	@DisplayName("Teste kontruktør")
	public void testConstructor() {
		assertEquals("APPL", stock.getTicker(), "Teste ticker");
		assertEquals(110.0, stock.getPrice(), "Teste aksjeprisen");

		assertThrows(IllegalArgumentException.class, () -> {
			new Stock(null, 110.0);
		}, "Teste kontruktør med null ticker");
	}

	@Test
	@DisplayName("Negativ aksjepris gir feilmelding")
	public void testSetNegativePrice() {
		assertThrows(IllegalArgumentException.class, () -> {
			stock.setPrice(-20.0);
		}, "Teste å sette negativ aksjepris");
	}

	@Test
	@DisplayName("Aksjepris lik null gir feilmelding")
	public void testSetZeroPrice() {
		assertThrows(IllegalArgumentException.class, () -> {
			stock.setPrice(0);
		}, "Teste å sette aksjepris lik null");
	}

	@Test
	@DisplayName("Legge til lytter")
	public void testStockListener() {
		StockListener listener = (Stock stock, double oldPrice, double newPrice) -> this
				.setPriceForListener(oldPrice, newPrice);
		stock.addStockListener(listener);

		this.setPriceCheckListener(118.0, 110.0, 118.0);
		assertEquals(118.0, stock.getPrice(), "Teste aksjepris etter å ha oppdatert pris");

		this.setPriceCheckListener(121.0, 118.0, 121.0);
		assertEquals(121.0, stock.getPrice(), "Teste aksjepris etter å ha oppdatert pris 2 ganger");
	}
}
