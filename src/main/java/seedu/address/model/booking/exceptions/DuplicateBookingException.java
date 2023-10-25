package seedu.address.model.booking.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateBookingException extends RuntimeException {

    /**
     * Constructs a DuplicateBookingException with a default error message.
     */
    public DuplicateBookingException() {
        super("Operation would result in duplicate persons");
    }
}
