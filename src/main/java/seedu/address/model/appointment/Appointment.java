package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.student.Name;

/**
 * Represents an appointment with a date and time, a student, and a description.
 */
public class Appointment {

    private final DateTime dateTime;
    private final Name name;
    private final Description description;

    /**
     * Constructs an Appointment object with the specified date and time, student, and description.
     *
     * @param dateTime    The date and time of the appointment.
     * @param name     The name of the student associated with the appointment.
     * @param description A description of the appointment.
     */
    public Appointment(DateTime dateTime, Name name, Description description) {
        requireAllNonNull(dateTime, name, description);
        this.dateTime = dateTime;
        this.name = name;
        this.description = description;
    }

    public DateTime getDateTime() {
        return this.dateTime;
    }

    public Name getName() {
        return this.name;
    }

    public Description getDescription() {
        return this.description;
    }

    /**
     * Returns true if both appointments have the same date and time.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getDateTime().equals(this.getDateTime())
                && other.getName().equals(this.getName());
    }

    /**
     * Returns true if both appointments have the same fields.
     */
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
        return this.name.equals(otherAppointment.name)
                && description.equals(otherAppointment.description)
                && dateTime.equals(otherAppointment.dateTime);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, dateTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("student", name)
                .add("description", description)
                .add("dateTime", dateTime)
                .toString();
    }
}
