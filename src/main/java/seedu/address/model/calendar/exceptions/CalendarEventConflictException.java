package seedu.address.model.calendar.exceptions;

import java.lang.Exception;

public class CalendarEventConflictException extends Exception {
    public CalendarEventConflictException(String message) {
        super(message);
    }
}
