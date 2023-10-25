package seedu.address.model.booking.exceptions;

/**
 * Signals that the operation is unable to find the specified Room for the booking.
 */
public class UniqueBookingListNotFoundException extends RuntimeException {

    /**
     * Constructs a UniqueBookingListNotFoundException with a default error message.
     */
    public UniqueBookingListNotFoundException() {
        super("Unique Booking List cannot be found");
    }
}
