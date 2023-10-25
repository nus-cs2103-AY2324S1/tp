package seedu.address.model.booking.exceptions;

/**
 * Exception for when a booking period is not found.
 */
public class BookingPeriodNotFoundException extends RuntimeException {

    /**
     * Constructs a BookingPeriodNotFoundException with a default error message.
     */
    public BookingPeriodNotFoundException() {
        super("Booking Period cannot be found");
    }
}
