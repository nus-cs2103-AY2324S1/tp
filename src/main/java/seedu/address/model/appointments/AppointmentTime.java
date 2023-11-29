package seedu.address.model.appointments;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the date and time of an appointment in ToothTracker.
 * Provides methods for manipulation and validation of appointment times.
 */
public class AppointmentTime {

    public static final String VALIDATION_REGEX = "[^\\s].*";
    private LocalDateTime start;
    private Duration duration;

    /**
     * Constructs an AppointmentTime object with the specified start time and duration.
     *
     * @param start The start time of the appointment in string format.
     * @param duration The duration of the appointment in string format.
     */
    public AppointmentTime(String start, String duration) {
        this.start = LocalDateTime.parse(start);
        String[] timeParts = duration.split(":");
        if (timeParts.length == 1) {
            this.duration = Duration.parse(duration);
        } else {
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            this.duration = Duration.ofHours(hours).plusMinutes(minutes);
        }

    }


    public LocalDateTime getStart() {
        return this.start;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public LocalDateTime getEnd() {
        return this.start.plus(duration);
    }

    /**
     * Checks whether this appointment clashes with another.
     *
     * @param other The other appointment.
     * @return true if this appointment clashes with another
     *         false if it does not clash with another appointment.
     */
    public boolean isClashingAppointment(AppointmentTime other) {
        if (other == this) {
            return true;
        }
        LocalDateTime otherStartTime = other.getStart();
        LocalDateTime otherEndTime = other.getEnd();
        return ((this.getStart().isBefore(otherEndTime))
                && (this.getStart().isAfter(otherStartTime)))
                || (otherStartTime.isEqual(this.getStart()))
                || ((this.getEnd().isBefore(otherEndTime))
                        && (this.getEnd().isAfter(otherStartTime)))
                || ((this.getStart().isAfter(otherStartTime)) && (this.getEnd().isBefore(otherEndTime)))
                || ((otherStartTime.isAfter(this.getStart())) && (otherEndTime.isBefore(this.getEnd())));
    }

    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String startToString() {
        return this.start.toString();
    }

    public String durationToString() {
        return this.duration.toString();
    }

    public String endTimeToString() {
        return getEnd().toLocalTime().toString();
    }

    public String startTimeToString() {
        return this.start.toLocalTime().toString();
    }

    public String dateToString() {
        return this.start.toLocalDate().toString();
    }
    public String appointmentTimeToString() {
        return startTimeToString() + " - " + endTimeToString();
    }

    /**
     * Converts the appointment start time to a formatted string representation.
     *
     * @return A string representation of the appointment start time in the format "yyyy-MM-dd HH:mm".
     */
    public String startString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = start.format(formatter);
        return formattedDateTime;
    }

    /**
     * Converts the duration of the appointment to a formatted string representation.
     *
     * @return A string representation of the duration of the appointment in the format "X hours and Y minutes",
     *         where X is the number of hours and Y is the number of minutes.
     */
    public String durationString() {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        String durationString = hours + " hours and " + minutes + " minutes";
        return durationString;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentTime)) {
            return false;
        }

        AppointmentTime otherAppointmentTime = (AppointmentTime) other;

        return appointmentTimeToString().equals(otherAppointmentTime.appointmentTimeToString());
    }

}
