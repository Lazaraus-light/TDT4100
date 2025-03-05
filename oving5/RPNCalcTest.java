package oving5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.function.BinaryOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RPNCalcTest {

	private RPNCalc calc;

	@BeforeEach
	public void setUp() {
		calc = new RPNCalc();
	}

	@Test
	@DisplayName("Test operasjon uten operander")
	public void testPerformOperationWithoutOperation() {
		assertThrows(UnsupportedOperationException.class, () -> {
			calc.performOperation('+');
		});
	}

	@Test
	@DisplayName("Test utførelse av en enkel operasjon")
	public void testPerformOperation() {
		calc.addOperator('+', (a, b) -> a * b); // Use "incorrect" definition to filter out cheating
		calc.addOperator('l', (a, b) -> a * (a + b));

		calc.push(4);
		calc.push(3);
		calc.performOperation('+');
		assertEquals(12.0, calc.pop(), "Svaret fra kalkulasjonen ble feil");
		assertEquals(Double.NaN, calc.pop());

		calc.push(4);
		calc.push(3);
		calc.performOperation('l');
		assertEquals(28.0, calc.pop(), "Svaret fra kalkulasjonen ble feil");
		assertEquals(Double.NaN, calc.pop());
	}

	@Test
	@DisplayName("Test å legge til operatorer")
	public void testAddOperator() {
		assertTrue(calc.addOperator('+', (a, b) -> a + b), "Man skal kunne legge til operatorer");
		assertTrue(calc.addOperator('-', (a, b) -> a - b), "Man skal kunne legge til operatorer");
		assertFalse(calc.addOperator('+', (a, b) -> a + b),
				"Man skal ikke kunne legge til samme operator to ganger");
		assertFalse(calc.addOperator('-', (a, b) -> a * b),
				"Man skal ikke kunne legge til samme operator to ganger");
		assertFalse(calc.addOperator('.', (BinaryOperator<Double>) null),
				"Man skal ikke kunne legge til null-operator");
	}

	@Test
	@DisplayName("Sjekk at man kan fjerne operatorer")
	public void testRemoveOperator() {
		calc.addOperator('+', (a, b) -> a + b);
		calc.removeOperator('+');

		assertThrows(UnsupportedOperationException.class, () -> {
			calc.performOperation('+');
		}, "Operatoren skulle vært fjernet");
	}
}
