package seedu.address.model.person.exceptions;

/**
 * Signals that the operation is unable to find the specified Email for the booking.
 */
public class EmailNotFoundException extends RuntimeException {

    /**
     * Constructs a EmailNotFoundException with a default error message.
     */
    public EmailNotFoundException() {
        super("Email cannot be found");
    }
}
