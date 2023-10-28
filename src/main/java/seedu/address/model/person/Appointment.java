package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's Appointment in the address book.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidAppointment(String)}
 */
public class Appointment {

    public static final String MESSAGE_CONSTRAINTS = "Appointment should be in the format "
            + "\"<Date> <Start Time> <End Time>\" and adhere to the following constraints:\n"
            + "1. Appointment should not be left blank. Remove this field if no Appointment is needed.\n"
            + "2. The <Date> field is formatted as \"<Day>-<Month>-<Year>\". It should be separated with dashes (-)"
            + " or not separated at all,\n    ie DD-MM-YY or DDMMYY.\n"
            + "    - <Day> is given as a simple numeric value.\n"
            + "    - <Month> is given as a simple numeric value, standard 3-letter month name, or full month name.\n"
            + "    - <Year> is optional. It is given as a 2 or 4 digit numeric value."
            + " If no year is given, the current year is assumed.\n"
            + "3. The 2 <Time> fields are formatted as \"<Hour>:<Minute>\". It should be separated by colons (:)"
            + " or not separated at all,\n    ie HHmm or HH:mm\n"
            + "4. <Start Time> should be earlier than or equals to <End Time>, and both are defined within <Date>."
            + "5. <Date>, <Start Time> and <End Time> are separated by spaces ( ) or commas (,).";

    public static final String FIELD_SEPARATOR_REGEX = "(,? )|,";

    public final String value;

    private static final String DATE_SEPARATOR = "[-]";
    private static final String TIME_SEPARATOR = "[:]";
    private static final String DAY_FIELD = "d";
    private static final String MONTH_FIELD = "M[MM[M]]";
    private static final String YEAR_FIELD = "[yy[yy]]";
    private static final String INPUT_DATE_FORMAT =
            DAY_FIELD + DATE_SEPARATOR + MONTH_FIELD + DATE_SEPARATOR + YEAR_FIELD;
    private static final String INPUT_TIME_FORMAT = "H" + TIME_SEPARATOR + "mm";
    public static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
    public static final DateTimeFormatter INPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern(INPUT_TIME_FORMAT);
    private static final String OUTPUT_DATE_FORMAT = "dd MMM yy, ";
    private static final String OUTPUT_TIME_FORMAT = "HH:mm";
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
    private static final DateTimeFormatter OUTPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_TIME_FORMAT);
    private final LocalDate date = null;
    private final LocalTime start = null;
    private final LocalTime end = null;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param appointment A valid appointment.
     */
    public Appointment(String appointment) {
        requireNonNull(appointment);
        checkArgument(isValidAppointment(appointment), MESSAGE_CONSTRAINTS);
        value = appointment;
    }

    /**
     * Returns true if a given string is a valid appointment.
     */
    public static boolean isValidAppointment(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
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
        return value.equals(otherAppointment.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
