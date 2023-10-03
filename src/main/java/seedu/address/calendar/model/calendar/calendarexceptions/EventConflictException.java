package seedu.address.calendar.model.calendar.calendarexceptions;

import java.lang.Exception;

public class EventConflictException extends Exception {
    public EventConflictException() {
        super("Conflicting time");
    }
}
