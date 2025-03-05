package oving5;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingleTicketTest {

    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        ticket = new SingleTicket();
    }

    @Test
    @DisplayName("Sjekk at billett returnerer true når den scannes for første gang")
    public void testReturnsTrueWhenScanned() {
        assertTrue(this.ticket.scan(),
                "Billett skal returnere true når det scannes for første gang");
    }

    @Test
    @DisplayName("Sjekk at billett returnerer false når den scannes for andre gang")
    public void testReturnsFalseWhenScannedTwice() {
        ticket.scan();
        assertFalse(ticket.scan(), "Billett skal returnere false når det scannes for andre gang");
    }

    @Test
    @DisplayName("Sjekk at billett returnerer true når den scannes for første gang etter reset")
    public void testReturnsFalseWhenScannedTwiceAfterReset() {
        ticket.scan();
        ticket = new SingleTicket();
        assertTrue(ticket.scan(),
                "Billett skal returnere true når det scannes for første gang etter reset");
    }
}
