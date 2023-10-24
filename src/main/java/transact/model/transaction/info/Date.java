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
    private final java.util.Date date;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Constructs a {@code Date}.
     *
     * @param dateString
     *            A string representation of a date in the format dd/mm/yyyy.
     */
    public Date(String dateString) {
        try {
            requireNonNull(dateString);
            if (!isValidDate(dateString)) {
                throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
            }
            this.date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns a Date object representing the date.
     */
    public java.util.Date getDate() {
        return this.date;
    }

    public static boolean isValidDate(String date) {
        return date.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return dateFormat.format(this.date);
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
        return date.equals(otherDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
