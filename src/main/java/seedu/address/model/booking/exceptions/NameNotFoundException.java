package seedu.address.model.booking.exceptions;

/**
 * Signals that the operation is unable to find the specified Name for the booking.
 */
public class NameNotFoundException extends RuntimeException {

    /**
     * Constructs a NameNotFoundException with a default error message.
     */
    public NameNotFoundException() {
        super("Name cannot be found");
    }
}
