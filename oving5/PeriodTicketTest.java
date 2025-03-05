package oving5;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PeriodTicketTest {

    @Test
    @DisplayName("Sjekk at billett er gyldig innenfor perioden")
    public void testIsValidWhenWithinPeriod() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        PeriodTicket ticket = new PeriodTicket(start, end);
        assertTrue(ticket.scan(), "Billett skal være gyldig innenfor perioden");
    }

    @Test
    @DisplayName("Sjekk at billett ikke er gyldig før perioden")
    public void testIsNotValidWhenBeforePeriod() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = LocalDateTime.now().plusDays(2);
        PeriodTicket ticket = new PeriodTicket(start, end);
        assertFalse(ticket.scan(), "Billett skal ikke være gyldig før perioden");
    }

    @Test
    @DisplayName("Sjekk at billett ikke er gyldig etter perioden")
    public void testIsNotValidWhenAfterPeriod() {
        LocalDateTime start = LocalDateTime.now().minusDays(2);
        LocalDateTime end = LocalDateTime.now().minusDays(1);
        PeriodTicket ticket = new PeriodTicket(start, end);
        assertFalse(ticket.scan(), "Billett skal ikke være gyldig etter perioden");
    }

    @Test
    @DisplayName("Sjekk at billett kaster IllegalArgumentException når startdato er etter sluttdato")
    public void testThrowsExceptionWhenStartIsAfterEnd() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = LocalDateTime.now().minusDays(1);

        assertThrows(IllegalArgumentException.class, () -> {
            new PeriodTicket(start, end);
        }, "Billett skal kaste IllegalArgumentException når startdato er etter sluttdato");
    }
}
