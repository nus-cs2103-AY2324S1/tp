package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.exceptions.InvalidStartEndTimeException;
import seedu.address.model.student.Name;

/**
 * Represents an appointment with a date, start time, end time, a student, and a description.
 */
public class Appointment implements Comparable<Appointment> {

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
    public Appointment(Date date, Time startTime, Time endTime, Name name, Description description)
            throws InvalidStartEndTimeException {
        requireAllNonNull(date, startTime, endTime, name, description);
        isValidStartEndTime(startTime, endTime);
        assert startTime.getLocalTime().isBefore(endTime.getLocalTime());
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
     * Checks the validity of the start and end times of this appointment.
     *
     * @param startTime The start time of the appointment.
     * @param endTime   The end time of the appointment.
     * @throws InvalidStartEndTimeException If the start time is not before the end time, an exception is thrown.
     */
    public void isValidStartEndTime(Time startTime, Time endTime) throws InvalidStartEndTimeException {
        if (!(startTime.getLocalTime().isBefore(endTime.getLocalTime()))) {
            throw new InvalidStartEndTimeException();
        }
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
     * Checks if this Appointment overlaps with another Appointment.
     * Two appointments overlap if both appointments simultaneously occur on the same date and time.
     */
    public boolean isOverlappingAppointment(Appointment other) {
        if (other == this) {
            return true;
        }

        if (!this.date.equals(other.getDate())) {
            return false;
        }

        if (this.endTime.getLocalTime().isAfter(other.startTime.getLocalTime())
                && other.endTime.getLocalTime().isAfter(this.startTime.getLocalTime())) {
            return true;
        }

        return false;
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

    /**
     * Compares this appointment with another appointment based on their date and start time.
     * If two appointments have the same date and start time, they are considered the same appointment, as there cannot
     * be overlapping appointments.
     *
     * @param otherAppointment The appointment to compare to.
     * @return A negative integer if this appointment is earlier than the other, a positive integer if it's later,
     *         and 0 if they are the same appointment.
     */
    @Override
    public int compareTo(Appointment otherAppointment) {
        int dateCompare = this.date.getLocalDate().compareTo(otherAppointment.date.getLocalDate());

        if (dateCompare != 0) {
            return dateCompare;
        }

        int startTimeCompare = this.startTime.getLocalTime().compareTo(otherAppointment.startTime.getLocalTime());

        if (startTimeCompare != 0) {
            return startTimeCompare;
        }

        assert this.equals(otherAppointment);

        return 0;
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
