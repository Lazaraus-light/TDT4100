package oving6.delegation.office;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManagerTest {

	private Clerk clerk;
	private Manager manager;
	private Printer printer;

	@BeforeEach
	public void setUp() {
		printer = new Printer();
		clerk = new Clerk(printer);
		manager = new Manager(List.of(clerk));
	}

	@Test
	@DisplayName("Lager Manager med tom Employee-samling")
	public void testNoEmployeesConstructor() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Manager(new ArrayList<Employee>());
		}, "Teste å lage ny Manager med tom Employee-samling");
	}

	@Test
	@DisplayName("Sjekker getResourceCount uten mellomledere")
	public void testResourceCount() {
		assertEquals(2, manager.getResourceCount(),
				"Teste antall ressurser for en manager uten mellomledere");
	}

	@Test
	@DisplayName("Sjekker getResourceCount med mellomledere")
	public void testMiddleManagementResourceCount() {
		Manager topManager = new Manager(List.of(manager));
		assertEquals(3, topManager.getResourceCount(), "Teste antall ressurser med mellomledere");
	}

	@Test
	@DisplayName("Gjør en beregning")
	public void testDoCalculations() {
		assertEquals(0, clerk.getTaskCount(),
				"Teste initialiseringen av antall oppgaver for arbeider");
		assertEquals(0, manager.getTaskCount(),
				"Teste initialiseringen av antall oppgaver for manager");

		double calc = manager.doCalculations((x, y) -> x + y, 2.0, 3.5);
		assertEquals(5.5, calc, "Teste beregning med addisjon: 2.0 + 3.5");

		assertEquals(1, clerk.getTaskCount(),
				"Teste antall oppgaver for arbeider etter 1 beregning");
		assertEquals(1, manager.getTaskCount(),
				"Teste antall oppgaver for manager etter 1 beregning");
	}

	@Test
	@DisplayName("Printe et dokument")
	public void testPrintDocuments() {
		manager.printDocument("dokument1");
		assertTrue(printer.getPrintHistory(clerk).contains("dokument1"),
				"Teste at printerhistorikken inneholder dokumentet som ble printet");
		assertEquals(1, clerk.getTaskCount(),
				"Teste antall oppgaver for arbeider etter 1 utskrift");
		assertEquals(1, manager.getTaskCount(),
				"Teste antall oppgaver for manager etter 1 utskrift");
		assertEquals(1, printer.getPrintHistory(clerk).size(),
				"Teste antall utskrifter i printerhistorikken etter 1 utskrift");
	}

	@Test
	@DisplayName("Sjekke antall oppgaver")
	public void testTaskCount() {
		assertEquals(0, clerk.getTaskCount(),
				"Teste initialisering av antall oppgaver for arbeider");
		assertEquals(0, manager.getTaskCount(),
				"Teste initialisering av antall oppgaver for manager");

		manager.printDocument("dokument");
		assertEquals(1, clerk.getTaskCount(),
				"Teste antall oppgaver for arbeider etter 1 utskrift");
		assertEquals(1, manager.getTaskCount(),
				"Teste antall oppgaver for manager etter 1 utskrift");

		manager.doCalculations((x, y) -> x + y, 2.0, 3.5);
		assertEquals(2, clerk.getTaskCount(),
				"Teste antall oppgaver for arbeider etter 1 utskrift og 1 beregning");
		assertEquals(2, manager.getTaskCount(),
				"Teste antall oppgaver for manager etter 1 utskrift og 1 beregning");
	}

	@Test
	@DisplayName("Flere arbeidere")
	public void testSeveralClerks() {
		Clerk secondClerk = new Clerk(printer);
		Manager multiManager = new Manager(List.of(clerk, secondClerk));
		assertEquals(1, clerk.getResourceCount(), "Teste antall ressurser for arbeider nr1");
		assertEquals(3, multiManager.getResourceCount(),
				"Teste antall ressurser for manager med to arbeidere");
		assertEquals(1, secondClerk.getResourceCount(), "Teste ressurser for arbeider nr2");

		multiManager.printDocument("dokument");
		assertEquals(1, multiManager.getTaskCount(),
				"Teste antall oppgaver for manager etter 1 utskrift");
		assertTrue(
				(clerk.getTaskCount() == 1 || secondClerk.getTaskCount() == 1)
						&& (clerk.getTaskCount() == 0 || secondClerk.getTaskCount() == 0),
				"Teste at kun en av arbeiderne har utført en oppgave etter 1 utskrift");

		double calc = multiManager.doCalculations((x, y) -> x + y, 2.0, 3.5);
		assertEquals(5.5, calc, "Teste beregning med addisjon: 2.0 + 3.5");
		assertEquals(2, multiManager.getTaskCount(),
				"Teste antall oppgaver for manager etter 1 utskrift og 1 beregning");
	}
}
