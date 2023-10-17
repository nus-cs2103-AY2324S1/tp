package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

public class AppointmentDescription {

    public static final String MESSAGE_CONSTRAINTS = "It is mandatory for the description to be filled. It should " +
            "only consist of alphanumeric characters and spaces.";

    private String description;

    /**
     *
     * @param description description to be included in the appointment
     */
    public AppointmentDescription(String description) {
        requireNonNull(description);
        this.description = description;
    }

    /**
     *
     * @param appointmentDescription
     * @return
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
            if (this.description.equals(otherAppointmentDescription.description)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

    public String toString() {
        return this.description;
    }
}
