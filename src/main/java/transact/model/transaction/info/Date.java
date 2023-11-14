package transact.model.transaction.info;

import static java.util.Objects.requireNonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date in the system.
 * Date should be in a valid format.
 */
public class Date {
    public static final String VALIDATION_REGEX = "\\d{2}/\\d{2}/\\d{2}"; // Date format (dd/MM/yy)
    public static final String MESSAGE_CONSTRAINTS = "Date must be in the format dd/MM/yy";
    public static final String MESSAGE_INVALIDDATE = "Invalid Date";
    private final java.util.Date date;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

    /**
     * Constructs a {@code Date}.
     *
     * @param dateString
     *            A string representation of a date in the format dd/MM/yy.
     */
    public Date(String dateString) {
        requireNonNull(dateString);
        try {
            this.date = dateFormat.parse(dateString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Returns a Date object representing the date.
     */
    public java.util.Date getDate() {
        return this.date;
    }
    /**
     * Checks if the given date string is valid according to the specified format "dd/MM/yy".
     *
     * @param date The date string to be validated.
     * @return {@code true} if the date is valid, {@code false} if it's invalid or the input format is incorrect.
     */
    public static boolean isValidDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            LocalDate localDate = LocalDate.parse(date, formatter);

            int year = localDate.getYear();
            int month = localDate.getMonthValue();
            int day = localDate.getDayOfMonth();
            int year2 = Integer.parseInt(date.substring(6)) + 2000;
            int month2 = Integer.parseInt(date.substring(3, 5));
            int day2 = Integer.parseInt(date.substring(0, 2));
            if ((year != year2) || (day != day2) || (month2 != month)) {
                return false;
            }

            if (year < 1000 || year > 9999 || month < 1 || month > 12) {
                return false;
            }

            int maxDay = localDate.lengthOfMonth();
            if (day < 1 || day > maxDay) {
                return false;
            }

            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
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
