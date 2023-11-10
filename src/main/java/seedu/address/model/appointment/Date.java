package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.util.DateTimeParser.INPUT_DATE_FORMATTER;

import java.time.DateTimeException;
import java.time.LocalDate;


/**
 * Represents the date of an appointment.
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should follow yyyy-MM-dd and must be a valid calendar date.";
    public static final String VALIDATION_REGEX =
            "^(20[0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
    public final String value;

    /** To facilitate filtering Appointments by date. **/
    public final LocalDate localDate;

    /**
     * Constructs a {@code Date}.a
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
        localDate = LocalDate.parse(date, INPUT_DATE_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }




    public String getDate() {
        return value;
    }

    public LocalDate getLocalDate() {
        return localDate;
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
        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date) other;
        return value.equals(otherDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
