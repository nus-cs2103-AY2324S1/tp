package transact.model.transaction.info;

import static java.util.Objects.requireNonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * Represents a date in the system.
 * Date should be in a valid format.
 */
public class Date {
    public static final String VALIDATION_REGEX = "\\d{2}/\\d{2}/\\d{4}"; // Date format (dd/mm/yyyy)
    public static final String MESSAGE_CONSTRAINTS = "Date must be in the format dd/mm/yyyy";
    private final String value;
    private final java.util.Date date;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Constructs a {@code Date}.
     *
     * @param date A string representation of a date in the format dd/mm/yyyy.
     */
    public Date(String date) {
        try {
            requireNonNull(date);
            if (!isValidDate(date)) {
                throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
            }
            value = date;
            this.date = dateFormat.parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns the date string in the format dd/mm/yyyy.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns a Date object representing the date.
     */
    public java.util.Date toDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
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

    public static boolean isValidDate(String date) {
        return date.matches(VALIDATION_REGEX);
    }
}
