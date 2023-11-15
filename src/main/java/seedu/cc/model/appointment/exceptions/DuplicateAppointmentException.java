package seedu.cc.model.appointment.exceptions;

/**
 * Signals that the operation will result in duplicate AppointmentEvent
 * (Appointments are considered duplicates if they have the same
 * identity).
 */
public class DuplicateAppointmentException extends RuntimeException {
    public DuplicateAppointmentException() {
        super("Operation would result in duplicate appointment");
    }
}
