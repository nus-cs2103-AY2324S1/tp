package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBookingPeriod(String)}
 */
public class BookingPeriod {

    public static final String MESSAGE_CONSTRAINTS = "Booking periods must be in the format 'YYYY-MM-DD HH:MM to "
            + "YYYY-MM-DD HH:MM', and the end datetime must be after or equal to the start datetime.";

    public static final String VALIDATION_REGEX = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2} to" +
            " \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$";

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
        checkArgument(isValidBookingPeriod(period), MESSAGE_CONSTRAINTS);
        value = period;
        setPeriod(period);
    }

    /**
     * Returns true if a given string is a valid booking period.
     * A valid booking period must be in the format "YYYY-MM-DD to YYYY-MM-DD".
     * The end date must be after or equal to the start date.
     *
     * @param test The string to test for validity.
     * @return True if the string matches the expected format and the end date is after or equal to the start date.
     */
    public static boolean isValidBookingPeriod(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        try {
            String[] dateTimeParts = test.split(" to ");
            LocalDateTime startDateTime = LocalDateTime.parse(dateTimeParts[0], dateTimeFormatter);
            LocalDateTime endDateTime = LocalDateTime.parse(dateTimeParts[1], dateTimeFormatter);
            return !endDateTime.isBefore(startDateTime);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Sets the start and end date and time for the booking period.
     *
     * @param period A valid period in the format.
     */
    private void setPeriod(String period) {
        try {
            // Split the string into start and end date parts
            String[] dateParts = period.split(" to ");
            this.checkInDateTime = LocalDateTime.parse(dateParts[0], dateTimeFormatter);
            this.checkOutDateTime = LocalDateTime.parse(dateParts[1], dateTimeFormatter);

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
