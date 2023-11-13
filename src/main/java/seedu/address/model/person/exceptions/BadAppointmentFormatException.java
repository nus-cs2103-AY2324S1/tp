package seedu.address.model.person.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Signals that Appointment has been given a bad value.
 */
public class BadAppointmentFormatException extends IllegalValueException {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public BadAppointmentFormatException(String message) {
        super(message);
    }

    /**
     * @param message should contain relevant information on the failed constraint(s)
     * @param cause of the main exception
     */
    public BadAppointmentFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
