package seedu.address.model.booking.exceptions;

/**
 * Signals that the operation is unable to find the specified Email for the booking.
 */
public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
        super("Email cannot be found");
    }
}
