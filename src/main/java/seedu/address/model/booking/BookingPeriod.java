package seedu.address.model.booking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBookingPeriod(String)}
 */
public class BookingPeriod {

    public static final String MESSAGE_CONSTRAINTS = "Booking periods must be in the format 'YYYY-MM-DD HH:MM to "
            + "YYYY-MM-DD HH:MM', and the end datetime must be after or equal to the start datetime.";

    public static final String VALIDATION_REGEX = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2} to"
            + " \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$";

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm",
                    Locale.US);

    // String representation of the booking period
    public final String value;

    private LocalDateTime checkInDateTime;

    private LocalDateTime checkOutDateTime;

    /**
     * Constructs a {@code BookingPeriod}.
     *
     * @param period A valid booking period.
     * @throws IllegalArgumentException If the period is null or doesn't match the expected format.
     */
    public BookingPeriod(String period) {
        if (period == null) {
            throw new IllegalArgumentException("Booking period cannot be null");
        }
        if (!isValidBookingPeriod(period)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        value = period;
        setPeriod(period);
    }

    /**
     * Returns true if a given string is a valid booking period.
     * A valid booking period must be in the format "YYYY-MM-DD HH:MM to YYYY-MM-DD HH:MM".
     * The end date must be after or equal to the start date.
     *
     * @param test The string to test for validity.
     * @return True if the string matches the expected format and the end date is after or equal to the start date.
     */
    public static boolean isValidBookingPeriod(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        String[] dateTimeParts = test.split(" to ");
        LocalDateTime startDateTime = LocalDateTime.parse(dateTimeParts[0], dateTimeFormatter);
        LocalDateTime endDateTime = LocalDateTime.parse(dateTimeParts[1], dateTimeFormatter);

        return isValidDate(dateTimeParts[0]) && isValidDate(dateTimeParts[1])
                    && !endDateTime.isBefore(startDateTime);
    }

    /**
     * Validates a date and time string in the "YYYY-MM-DD HH:MM" format.
     *
     * @param dateTimeString The string to be validated as a date and time.
     * @return True if the input string is a valid date and time, false otherwise.
     */
    static boolean isValidDate(String dateTimeString) {
        String[] parts = dateTimeString.split(" ");

        if (parts.length != 2) {
            return false; // Invalid format, should have a date and time part
        }

        String datePart = parts[0];
        String timePart = parts[1];

        if (datePartIsValid(datePart) && timePartIsValid(timePart)) {
            return true;
        }

        return false;
    }

    /**
     * Validates a date string in the "YYYY-MM-DD" format.
     *
     * @param datePart The date part of the date and time string.
     * @return True if the date part is valid, false otherwise.
     */
    static boolean datePartIsValid(String datePart) {
        String[] dateParts = datePart.split("-");

        if (dateParts.length != 3) {
            return false; // Invalid format, should have year, month, day parts
        }

        try {
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);

            if (year >= 0 && year <= 9999 && month >= 1 && month <= 12 && day >= 1) {
                if (month == 2) {
                    // Check for leap year
                    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                        return day <= 29;
                    } else {
                        return day <= 28;
                    }
                } else if ((month == 4 || month == 6 || month == 9 || month == 11)) {
                    return day <= 30;
                } else {
                    return day <= 31;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    /**
     * Validates a time string in the "HH:MM" format.
     *
     * @param timePart The time part of the date and time string.
     * @return True if the time part is valid, false otherwise.
     */
    static boolean timePartIsValid(String timePart) {
        String[] timeParts = timePart.split(":");

        if (timeParts.length != 2) {
            return false; // Invalid format, should have hour and minute parts
        }

        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
    }



    /**
     * Sets the start and end date and time for the booking period.
     *
     * @param period A valid period in the format.
     * @throws IllegalArgumentException If the period is not in the expected format or violates constraints.
     */
    private void setPeriod(String period) {
        try {
            // Split the string into start and end date parts
            String[] dateParts = period.split(" to");
            LocalDateTime startDateTime = LocalDateTime.parse(dateParts[0].trim(), dateTimeFormatter);
            LocalDateTime endDateTime = LocalDateTime.parse(dateParts[1].trim(), dateTimeFormatter);

            // Check if endDateTime is after or equal to startDateTime
            if (endDateTime.isBefore(startDateTime) || !isValidDate(dateParts[0]) || !isValidDate(dateParts[0])) {
                throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
            }

            this.checkInDateTime = startDateTime;
            this.checkOutDateTime = endDateTime;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS, e);
        }
    }

    /**
     * Returns a string representation of this BookingPeriod.
     *
     * @return The string representation of this BookingPeriod.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Compares this BookingPeriod with the specified object for equality.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookingPeriod)) {
            return false;
        }

        BookingPeriod otherBookingPeriod = (BookingPeriod) other;
        return value.equals(otherBookingPeriod.value);
    }

    /**
     * Returns a hash code value for this BookingPeriod.
     *
     * @return A hash code value for this BookingPeriod.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
