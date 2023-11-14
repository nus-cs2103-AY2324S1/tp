package seedu.address.model.appointment;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.DateUtil.dateTimeToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Represents the time slot for an appointment in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointmentTime(LocalDateTime, LocalDateTime)}
 */
public class AppointmentTime implements Comparable<AppointmentTime> {
    public static final String MESSAGE_CONSTRAINTS =
            "1. AppointmentTime start must be before AppointmentTime end.\n"
            + "2. AppointmentTime must also not overlap with an existing Appointment's time.\n"
            + "3. Ensure that the AppointmentTime start and AppointmentTime end are valid date times.\n"
            + "* Note: Date indicated must be YYYY/MM/DD"
            + "(i.e. 2th Jan 2020 must be input as 2021/01/02 instead of 2020-01-01).\n"
            + "* Note: Time indicated must be XX:XX (i.e. 9AM must be input as 09:00 instead of 9:00).\n"
            + "E.g.: start=2023/10/13 09:00 end=2023/10/13 12:00";

    // Data fields
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an {@code AppointmentTime}.
     *
     * @param start Start time of the appointment.
     * @param end End time of the appointment.
     */
    public AppointmentTime(LocalDateTime start, LocalDateTime end) {
        requireAllNonNull(start, end);
        checkArgument(isValidAppointmentTime(start, end), MESSAGE_CONSTRAINTS);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Returns true if a given start and end time form a valid appointment time.
     */
    public static boolean isValidAppointmentTime(LocalDateTime start, LocalDateTime end) {
        requireAllNonNull(start, end);
        return !start.isAfter(end) && !start.isEqual(end);
    }

    /**
     * Returns true if the provided appointment does not overlap with any appointment in the appointment list.
     *
     * @param appointmentList Current list of appointments to check against.
     * @param appointment The appointment to check.
     * @return True if the appointment is valid, false otherwise.
     */
    public static Boolean isValidTimeSlot(List<Appointment> appointmentList, Appointment appointment) {
        LocalDateTime start = appointment.getStartTime();
        LocalDateTime end = appointment.getEndTime();

        for (Appointment currentAppointment : appointmentList) {
            LocalDateTime currentStart = currentAppointment.getStartTime();
            LocalDateTime currentEnd = currentAppointment.getEndTime();

            if (start.isEqual(currentStart) || end.isEqual(currentEnd)
                    || start.isAfter(currentStart) && start.isBefore(currentEnd)
                    || end.isAfter(currentStart) && end.isBefore(currentEnd)
                    || start.isBefore(currentStart) && end.isAfter(currentEnd)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AppointmentTime // instanceof handles nulls
                && getStart().isEqual(((AppointmentTime) other).getStart()) // check same data fields
                && getEnd().isEqual(((AppointmentTime) other).getEnd()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public int compareTo(AppointmentTime otherAppointmentTime) {
        return start.compareTo(otherAppointmentTime.start);
    }
    @Override
    public String toString() {
        return "START: " + dateTimeToString(start) + "\nEND: " + dateTimeToString(end);
    }

}
