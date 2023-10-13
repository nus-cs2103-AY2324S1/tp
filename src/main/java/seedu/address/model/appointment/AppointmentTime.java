package seedu.address.model.appointment;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import javafx.collections.ObservableList;

/**
 * Represents the time slot for an appointment in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointmentTime(LocalDateTime, LocalDateTime)}
 */
public class AppointmentTime {
    public static final String MESSAGE_CONSTRAINTS =
            "1. AppointmentTime start must be before AppointmentTime end.\n"
                    + "2. AppointmentTime start and end should be at most 24 hours apart.\n"
                    + "3. AppointmentTime must also not overlap with an existing Appointment's time.\n"
                    + "* Note: Date indicated must be DD/MM/YYYY"
                    + "(i.e. 2th Jan 2020 must be input as 02/01/2021 instead of 2020-01-01).\n"
                    + "* Note: Time indicated must be XX:XX (i.e. 9AM must be input as 09:00 instead of 9:00).\n"
                    + "Eg: start=13/10/2023 09:00 end=13/10/2023 12:00";

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
        checkArgument(isValidAppointmentTime(start, end), MESSAGE_CONSTRAINTS);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns true if a given start and end time form a valid appointment time.
     */
    public static boolean isValidAppointmentTime(LocalDateTime start, LocalDateTime end) {
        requireAllNonNull(start, end);
        if (start.isAfter(end)) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the provided appointment does not overlap with any appointment in the appointment list.
     *
     * @param appointmentList Current list of appointments to check against.
     * @param appointment The appointment to check.
     * @return True if the appointment is valid, false otherwise.
     */
    public static Boolean isValidTimeSlot(ObservableList<Appointment> appointmentList, Appointment appointment) {
        LocalDateTime startDate = appointment.getStartTime();
        LocalDateTime endDate = appointment.getEndTime();

        for (Appointment currentAppointment : appointmentList) {
            LocalDateTime currentStartDate = currentAppointment.getStartTime();
            LocalDateTime currentEndDate = currentAppointment.getEndTime();
            // startDate or endDate is in between currentAppointment slot
            // current Appointment slot is within new appointment slot
            if (startDate.isEqual(currentStartDate) || endDate.isEqual(currentEndDate)
                    || startDate.isAfter(currentStartDate) && startDate.isBefore(currentEndDate)
                    || endDate.isAfter(currentStartDate) && endDate.isBefore(currentEndDate)
                    || startDate.isBefore(currentStartDate) && endDate.isAfter(currentEndDate)) {
                return false;
            }
        }
        return true;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentTime // instanceof handles nulls
                && getStart().isEqual(((AppointmentTime) other).getStart()) // check same data fields
                && getEnd().isEqual(((AppointmentTime) other).getEnd()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "START TIME: " + start + "\n END TIME: " + end;
    }

}
