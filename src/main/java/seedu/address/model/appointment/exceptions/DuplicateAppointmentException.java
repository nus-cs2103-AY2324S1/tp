package seedu.address.model.appointment.exceptions;

/**
 * Signals that the operation would result in duplicate Appointments.
 * Duplicate Appointments are considered appointments that have the same identity.
 */
public class DuplicateAppointmentException extends RuntimeException {
    /**
     * Constructs a new {@code DuplicateAppointmentException} with the default detail message.
     */
    public DuplicateAppointmentException() {
        super("Operation results in duplicate appointments");
    }
}
