package seedu.address.model.event.exceptions;


/**
 * This exception is thrown when an event period is invalid.
 */
public class InvalidEventPeriodException extends RuntimeException {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public InvalidEventPeriodException(String message) {
        super(message);
    }
}
