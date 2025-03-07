package oving5.Ticket;

public class SingleTicket implements Ticket {
    private boolean valid;

    public SingleTicket() {
        this.valid = true;
    }

    @Override
    public boolean scan() {
        if (valid) {
            valid = false;
            return true;
        }
        return false;
    }
}
