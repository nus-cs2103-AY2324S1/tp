package seedu.address.model.booking.exceptions;

/**
 * Signals that the operation is unable to find the specified Phone for the booking.
 */
public class PhoneNotFoundException extends RuntimeException {

    /**
     * Constructs a PhoneNotFoundException with a default error message.
     */
    public PhoneNotFoundException() {
        super("Phone cannot be found");
    }
}
