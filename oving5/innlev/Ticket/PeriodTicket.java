package oving5.Ticket;

import java.time.LocalDateTime;

public class PeriodTicket implements Ticket {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public PeriodTicket(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Start- og sluttid kan ikke være null.");
        }
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("Sluttid må være etter starttid.");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean scan() {
        LocalDateTime now = LocalDateTime.now();
        return (now.isEqual(startTime) || now.isAfter(startTime)) &&
               (now.isEqual(endTime)   || now.isBefore(endTime));
    }
}
