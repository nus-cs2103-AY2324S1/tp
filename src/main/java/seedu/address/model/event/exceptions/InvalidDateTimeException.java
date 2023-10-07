package seedu.address.model.event.exceptions;

import java.lang.Exception;

public class InvalidDateTimeException extends Exception {
    public InvalidDateTimeException(String message) {
        super(message);
    }
}
