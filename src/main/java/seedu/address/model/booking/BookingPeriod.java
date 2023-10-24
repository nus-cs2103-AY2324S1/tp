package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;

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

    public final String value; // String representation of the booking period

    private LocalDateTime checkInDateTime;

    private LocalDateTime checkOutDateTime;

    /**
     * Constructs an {@code Address}.
     *
     * @param period A valid booking period.
     */
    public BookingPeriod(String period) {
        requireNonNull(period);
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

        String[] dateParts = datePart.split("-");
        String[] timeParts = timePart.split(":");

        if (dateParts.length != 3 || timeParts.length != 2) {
            return false; // Invalid format, should have year, month, day, hour, and minute parts
        }

        try {
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);

            if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59 && month >= 1 && month <= 12 && day >= 1) {
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
     * Sets the start and end date and time for the booking period.
     *
     * @param period A valid period in the format.
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
        if (!(other instanceof BookingPeriod)) {
            return false;
        }

        BookingPeriod otherBookingPeriod = (BookingPeriod) other;
        return value.equals(otherBookingPeriod.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
