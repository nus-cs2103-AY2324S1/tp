package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Appointment in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointment(String)}
 */
public class Appointment {

    public static final String MESSAGE_CONSTRAINTS = "Appointment can take any values, and it should not be blank";
    public static final String MESSAGE_CONSTRAINTS_START =
            "Start time should be in the format DD-MM-YYYY HH:MM, ze.g., 01-01-2023 09:45";
    public static final String MESSAGE_CONSTRAINTS_END = "End time should be in the format HH:MM, e.g., 20:30";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^$|[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param appointment A valid appointment.
     */
    public Appointment(String appointment) {
        requireNonNull(appointment);
        checkArgument(isValidAppointment(appointment), MESSAGE_CONSTRAINTS);
        value = appointment;
    }

    /**
     * Returns true if a given string is a valid appointment.
     */
    public static boolean isValidAppointment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isValidStart(String start) {
        return start.matches("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}");
    }

    public static boolean isValidEnd(String end) {
        return end.matches("\\d{2}:\\d{2}");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return value.equals(otherAppointment.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
