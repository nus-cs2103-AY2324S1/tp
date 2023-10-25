package seedu.address.model.booking.exceptions;

/**
 * Signals that the operation is unable to find the specified Room for the booking.
 */
public class RoomNotFoundException extends RuntimeException {

    /**
     * Constructs a RoomNotFoundException with a default error message.
     */
    public RoomNotFoundException() {
        super("Room cannot be found");
    }
}
