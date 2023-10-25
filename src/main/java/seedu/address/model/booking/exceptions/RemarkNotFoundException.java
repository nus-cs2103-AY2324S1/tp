package seedu.address.model.booking.exceptions;

public class RemarkNotFoundException extends RuntimeException {

    /**
     * Constructs a BookingNotFoundException with a default error message.
     */
    public RemarkNotFoundException() {
        super("Remark cannot be found");
    }
}
