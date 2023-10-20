package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an Appointment in the address book.
 */
public class Appointment {
    public static final String MESSAGE_DATE_CONSTRAINTS = "Input Date should be in format of dd-MM-yyyy HH:mm";
    public static final String MESSAGE_INVALID_DATE = "Please ensure you input a valid date and time";
    public static final String MESSAGE_DESC_CONSTRAINTS = "Appointment description should only contain alphanumeric "
            + "characters and spaces, and it should not be blank";
    public static final String MESSAGE_APT_CONSTRAINTS = "Invalid appointment string. "
            + "Should be (description), (date) (time)";
    public static final String VALIDATION_DATE_REGEX = "\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}";
    public static final String VALIDATION_DESC_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String VALIDATION_APT_REGEX = "^([\\p{Alnum}][\\p{Alnum} ]*), (\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2})$";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public final String value;
    public final LocalDateTime date;

    /**
     * Constructs a {@code Appointment}
     *
     * @param value A valid description of the Appointment.
     * @param date A valid LocalDateTime object representing Appointment date.
     */
    public Appointment(String value, LocalDateTime date) {
        requireNonNull(value);
        this.value = value;
        this.date = date;
    }

    @Override
    public String toString() {
        return value + ", " + date.format(DATE_FORMATTER);
    }

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
        return value.equals(otherAppointment.value)
                && date.equals(otherAppointment.date);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value, date);
    }
    public static boolean isValidDesc(String desc) {
        return desc.matches(VALIDATION_DESC_REGEX);
    }
    public static boolean isValidDate(String date) {
        return date.matches(VALIDATION_DATE_REGEX);
    }
    public static boolean isValidAppointment(String appointment) {
        return appointment.matches(VALIDATION_APT_REGEX);
    }

    /**
     * Returns the LocaleDateTime object representing the date and time of the Appointment.
     *
     * @param input The input date and time from user.
     * @return The LocaleDateTime object representing Appointment date.
     * @throws IllegalArgumentException If input date does not match specified format.
     */
    public static LocalDateTime parseAppointmentDate(String input) throws IllegalArgumentException {
        return LocalDateTime.parse(input, DATE_FORMATTER);
    }

    /**
     * Returns an Appointment after parsing the string representation of the appointment.
     *
     * @param appointment The string representation of the appointment.
     * @return The Appointment object.
     */
    public static Appointment parseAppointmentDescription(String appointment) {
        Logger logger = Logger.getLogger(Appointment.class.getName());
        Pattern pattern = Pattern.compile(VALIDATION_APT_REGEX);
        Matcher matcher = pattern.matcher(appointment);
        logger.info("Matches: " + matcher.matches());
        String valueField = matcher.group(1).trim();
        String dateField = matcher.group(2).trim();

        return new Appointment(valueField, parseAppointmentDate(dateField));
    }
}
