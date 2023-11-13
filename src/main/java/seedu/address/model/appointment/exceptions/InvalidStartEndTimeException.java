package seedu.address.model.appointment.exceptions;

/**
 * Handle appointments with start time after end time.
 */
public class InvalidStartEndTimeException extends Exception {
    public InvalidStartEndTimeException() {
        super("Start time cannot be after end time");
    }
}
