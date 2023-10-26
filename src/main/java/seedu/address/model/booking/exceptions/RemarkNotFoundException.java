package seedu.address.model.booking.exceptions;

/**
 * Signals that the operation is unable to find the specified Remark for the booking.
 */
public class RemarkNotFoundException extends RuntimeException {

    /**
     * Constructs a RemarkNotFoundException with a default error message.
     */
    public RemarkNotFoundException() {
        super("Remark cannot be found");
    }
}
