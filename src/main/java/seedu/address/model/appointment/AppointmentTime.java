package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


/**
 * Represents the time of an appointment.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointmentTime(String)}
 */
public class AppointmentTime implements Comparable<AppointmentTime> {

    public static final String MESSAGE_CONSTRAINTS =
            "AppointmentTime should be valid date and time in the format of yyyy-mm-dd HH:mm\n";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);
    public final LocalDateTime value;

    /**
     * Constructs a {@code AppointmentTime}.
     *
     * @param appointmentTime A valid ic.
     */
    public AppointmentTime(String appointmentTime) {
        requireNonNull(appointmentTime);
        checkArgument(isValidAppointmentTime(appointmentTime), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(appointmentTime, FORMATTER);
    }

    /**
     * Returns true if a given string is a valid appointmentTime.
     */
    public static boolean isValidAppointmentTime(String test) {
        try {
            LocalDateTime.parse(test, FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value.format(FORMATTER);
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
        return value.equals(otherAppointmentTime.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(AppointmentTime o) {
        return this.value.compareTo(o.value);
    }
}
