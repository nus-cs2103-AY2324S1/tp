package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Objects;
import java.util.function.Function;

import seedu.address.model.person.enums.InputSource;
import seedu.address.model.person.exceptions.BadAppointmentFormatException;

/**
 * Represents a Person's Appointment in the address book.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidAppointmentDelimit(String, InputSource)}
 */
public class Appointment {

    public static final Appointment TODAY =
            new Appointment(LocalDate.now(), LocalTime.of(0, 0), LocalTime.of(23, 59));

    public static final String MESSAGE_CONSTRAINTS = "Appointment should be in the format "
            + "\"<Date> <Start Time> <End Time>\" and adhere to the following constraints:\n"
            + "1. Appointment should not be left blank. Remove this field if no Appointment is needed.\n"
            + "2. The <Date> field is formatted as \"<Day>-<Month>-<Year>\". It should be separated with dashes (-).\n"
            + "    ie DD-MM-YYYY.\n"
            + "    - <Day> is given as a simple numeric value.\n"
            + "    - <Month> is given as a simple numeric value or a standard 3-letter month name.\n"
            + "    - <Year> is optional. It is given as a 2 or 4 digit numeric value."
            + " If no year is given, the current year is assumed.\n"
            + "3. The 2 <Time> fields are formatted as \"<Hour>:<Minute>\". It should be separated by colons (:) or not"
            + "separated at all.\n"
            + "    if no colon is separated, hour should have a leading 0 if relevant. ie HHmm or HH:mm\n"
            + "4. <Start Time> should be earlier than or equals to <End Time>, and both are defined within <Date>.\n"
            + "5. <Date>, <Start Time> and <End Time> are separated by spaces ( ) or commas (,).";

    public static final String UNRECOGNIZED_SOURCE = "Unrecognised input source detected in Appointment.";
    public static final String FIELD_SEPARATOR_REGEX = "(,? )|(,)";

    private static final DateTimeFormatter INPUT_DATE_FORMATTER = new DateTimeFormatterBuilder()
            .optionalStart()
            .appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE)
            .appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR, 1, 2, SignStyle.NOT_NEGATIVE)
            .optionalStart()
            .appendLiteral('-').appendValueReduced(ChronoField.YEAR, 2, 4, 2000)
            .optionalEnd().optionalEnd()
            .optionalStart()
            .appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE)
            .appendLiteral('-').appendText(ChronoField.MONTH_OF_YEAR, TextStyle.SHORT)
            .optionalStart()
            .appendLiteral('-').appendValueReduced(ChronoField.YEAR, 2, 4, 2000)
            .optionalEnd().optionalEnd()
            .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear()).toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter INPUT_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.HOUR_OF_DAY, 1, 2, SignStyle.NOT_NEGATIVE)
            .optionalStart().appendLiteral(":").optionalEnd()
            .optionalStart().appendValue(ChronoField.MINUTE_OF_HOUR, 2).optionalEnd()
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter().withResolverStyle(ResolverStyle.STRICT);
    private static final String STORAGE_FIELD_SEPARATOR = ", ";
    private static final String STORAGE_DATE_FORMAT = "dd-MMM-uuuu";
    private static final String STORAGE_TIME_FORMAT = "HH:mm";
    private static final DateTimeFormatter STORAGE_DATE_FORMATTER = DateTimeFormatter.ofPattern(STORAGE_DATE_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter STORAGE_TIME_FORMATTER = DateTimeFormatter.ofPattern(STORAGE_TIME_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);
    private static final String MODEL_PERIOD_SEPARATOR = " to ";
    private final LocalDate date;
    private final LocalTime start;
    private final LocalTime end;

    private Appointment(LocalDate date, LocalTime start, LocalTime end) {
        requireAllNonNull(date, start, end);
        this.date = date;
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an {@code Appointment}.
     *
     * @param appointment An appointment String input.
     * @param origin The enumeration defining where the method was called from.
     */
    public static Appointment of(String appointment, InputSource origin) throws BadAppointmentFormatException {
        requireNonNull(appointment);
        Function<String, LocalDate> dateFormatter;
        Function<String, LocalTime> timeFormatter;
        String fieldSeparator;
        switch (origin) {
        case USER_INPUT:
            dateFormatter = (val) -> LocalDate.parse(val, INPUT_DATE_FORMATTER);
            timeFormatter = (val) -> LocalTime.parse(val, INPUT_TIME_FORMATTER);
            fieldSeparator = FIELD_SEPARATOR_REGEX;
            break;
        case STORAGE:
            dateFormatter = (val) -> LocalDate.parse(val, STORAGE_DATE_FORMATTER);
            timeFormatter = (val) -> LocalTime.parse(val, STORAGE_TIME_FORMATTER);
            fieldSeparator = "(" + STORAGE_FIELD_SEPARATOR + ")";
            break;
        default:
            throw new IllegalStateException(UNRECOGNIZED_SOURCE);
        }

        checkArgument(isValidAppointmentDelimit(appointment, origin), MESSAGE_CONSTRAINTS);
        String[] splitFields = appointment.split(fieldSeparator);
        LocalDate date;
        LocalTime start;
        LocalTime end;
        try {
            date = dateFormatter.apply(splitFields[0]);
            start = timeFormatter.apply(splitFields[1]);
            end = timeFormatter.apply(splitFields[2]);
        } catch (DateTimeParseException e) {
            throw new BadAppointmentFormatException(MESSAGE_CONSTRAINTS, e);
        }

        if (start.isAfter(end)) {
            throw new BadAppointmentFormatException(MESSAGE_CONSTRAINTS);
        }

        return new Appointment(date, start, end);
    }

    /**
     * Returns true if a given input string is delimited properly based on its source.
     */
    public static boolean isValidAppointmentDelimit(String test, InputSource origin) {
        switch (origin) {
        case USER_INPUT:
            return test.split(FIELD_SEPARATOR_REGEX).length == 3;
        case STORAGE:
            return test.split("(" + STORAGE_FIELD_SEPARATOR + ")").length == 3;
        default:
            throw new IllegalStateException(UNRECOGNIZED_SOURCE);
        }
    }

    /**
     * Returns true if the period represented by this {@code Appointment} overlaps with the period
     * of {@code target} on the same day, inclusive of the {@code start} and {@code end}.<br>
     *
     * This method defines an incompatibility relationship between 2 {@code Appointment} objects.
     */
    public boolean hasOverlap(Appointment target) {
        assert !start.isAfter(end);
        if (target == null) {
            return false;
        }

        assert !target.start.isAfter(target.end);
        if (!date.isEqual(target.date)) {
            return false;
        }
        return !end.isBefore(target.start) && !start.isAfter(target.end);
    }

    /**
     * @return The String representation of {@code Appointment} for use in Storage/Save-Parsing
     */
    public String toSaveString() {
        return STORAGE_DATE_FORMATTER.format(date) + STORAGE_FIELD_SEPARATOR
                + STORAGE_TIME_FORMATTER.format(start) + STORAGE_FIELD_SEPARATOR + STORAGE_TIME_FORMATTER.format(end);
    }

    @Override
    public String toString() {
        return STORAGE_DATE_FORMATTER.format(date) + STORAGE_FIELD_SEPARATOR
                + STORAGE_TIME_FORMATTER.format(start) + MODEL_PERIOD_SEPARATOR + STORAGE_TIME_FORMATTER.format(end);
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
        return date.equals(otherAppointment.date)
                && start.equals(otherAppointment.start)
                && end.equals(otherAppointment.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, start, end);
    }
}
