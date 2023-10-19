package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Appointment's description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointmentDescription(String)}
 */
public class AppointmentDescription {

    public static final String MESSAGE_CONSTRAINTS =
            "Description should only contain alphanumeric characters and spaces, and it should not be blank";

    public final String value;

    /**
     * Constructor method
     * @param description description to be included in the appointment
     */
    public AppointmentDescription(String description) {
        requireNonNull(description);
        this.value = description;
    }

    /**
     * Checks for validity of characters in appointment description
     *
     * @param appointmentDescription string text by user input
     * @return whether the string only contains alphanumeric characters, numbers and hyphens
     */
    public static boolean isValidAppointmentDescription(String appointmentDescription) {
        return appointmentDescription.matches("^[a-zA-Z0-9 -]+$");
    }

    @Override
    public boolean equals(Object other) {
        // if this and the other are the same object
        if (this == other) {
            return true;
        }

        if (other instanceof AppointmentDescription) {
            AppointmentDescription otherAppointmentDescription = (AppointmentDescription) other;
            if (this.value.equals(otherAppointmentDescription.value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return this.value;
    }
}
