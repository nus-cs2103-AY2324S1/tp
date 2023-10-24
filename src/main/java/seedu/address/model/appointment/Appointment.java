package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.student.Name;

/**
 * Represents an appointment with a date, start time, end time, a student, and a description.
 */
public class Appointment {

    private final Date date;
    private final Time startTime;
    private final Time endTime;
    private final Name name;
    private final Description description;

    /**
     * Constructs an Appointment object with the specified date, start time, end time, student, and description.
     *
     * @param date        The date of the appointment.
     * @param startTime   The start time of the appointment.
     * @param endTime     The end time of the appointment.
     * @param name        The name of the student associated with the appointment.
     * @param description A description of the appointment.
     */
    public Appointment(Date date, Time startTime, Time endTime, Name name, Description description) {
        requireAllNonNull(date, startTime, endTime, name, description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.description = description;
    }

    public Date getDate() {
        return this.date;
    }

    public Time getStartTime() {
        return this.startTime;
    }

    public Time getEndTime() {
        return this.endTime;
    }

    public Name getName() {
        return this.name;
    }

    public Description getDescription() {
        return this.description;
    }

    /**
     * Returns true if both appointments have the same date, start time, end time, and name.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getDate().equals(this.getDate())
                && other.getStartTime().equals(this.getStartTime())
                && other.getEndTime().equals(this.getEndTime())
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

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return this.name.equals(otherAppointment.name)
                && description.equals(otherAppointment.description)
                && date.equals(otherAppointment.date)
                && startTime.equals(otherAppointment.startTime)
                && endTime.equals(otherAppointment.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, date, startTime, endTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("student", name)
                .add("description", description)
                .add("date", date)
                .add("startTime", startTime)
                .add("endTime", endTime)
                .toString();
    }
}
