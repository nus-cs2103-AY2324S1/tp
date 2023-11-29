package seedu.address.model.appointments;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Date in the address book. Guarantees: immutable; is valid as declared in
 * {@link #isValidDate(String)}
 */
public class AppointmentDate {

    public static final String MESSAGE_CONSTRAINTS = "Appointment date should be in the following format: dd-mm-yy";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code AppointmentDate}.
     *
     * @param date A valid date
     */
    public AppointmentDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns true if a given string is valid
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
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
        if (!(other instanceof AppointmentDate)) {
            return false;
        }

        AppointmentDate otherBirthdate = (AppointmentDate) other;
        return value.equals(otherBirthdate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}


