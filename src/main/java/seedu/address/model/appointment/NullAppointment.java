package seedu.address.model.appointment;

/**
 * An empty Appointment Object to represent Appointments that have not been made.
 */
public class NullAppointment extends Appointment {
    public static final String MESSAGE_NULL_APT = "No Appointment made!";
    public NullAppointment() {
        super("", null);
    }
    @Override
    public String toString() {
        return MESSAGE_NULL_APT;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NullAppointment)) {
            return false;
        }

        return true;
    }
}
