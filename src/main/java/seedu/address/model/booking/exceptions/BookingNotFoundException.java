package seedu.address.model.booking.exceptions;

/**
 * Signals that the operation is unable to find the specified booking.
 */
public class BookingNotFoundException extends RuntimeException {

    /**
     * Constructs a BookingNotFoundException with a default error message.
     */
    public BookingNotFoundException() {
        super("Booking cannot be found");
    }
}
