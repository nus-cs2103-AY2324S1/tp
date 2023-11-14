package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Represents an Event's date and time in the Event list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS = "DateTime must be in the format DD/MM/YYYY TTTT, "
                                                        + "and it should not be blank";

    /*
     * The first character of the dateTime must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * dateTime must also be in the format "DD/MM/YYYY TTTT"
     */
    public static final String VALIDATION_REGEX = "\\d{2}/\\d{2}/\\d{4} \\d{4}";

    public final LocalDateTime dateAndTime;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid dateTime.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateAndTime = setDateTime(dateTime);
    }

    /**
     * Constructs a {@code DateTime} from an existing LocalDateTime object.
     *
     * @param dateTime A valid LocalDateTime object.
     */
    public DateTime(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        this.dateAndTime = dateTime;
    }

    /**
     * Returns true if a given string is a valid budget.
     */
    public static boolean isValidDateTime(String dateAndTime) {
        try {
            requireNonNull(dateAndTime);
            String trimmedDateAndTime = dateAndTime.trim();
            String[] parts = dateAndTime.split(" ");

            // If there isn't exactly two components in the dateAndTime, return false
            if (parts.length != 2) {
                return false;
            }

            String date = parts[0];
            String time = parts[1];

            String[] dateParts = date.split("/");
            // If the date does not have three components, return false
            if (dateParts.length != 3) {
                return false;
            }

            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            // If time is not a four-digit number, return false
            if (time.length() != 4) {
                return false;
            }

            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(2, 4));
            LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, min);
        } catch (NullPointerException e) { // If the argument is null
            return false;
        } catch (NumberFormatException e) { // If date and time values are not integers
            return false;
        } catch (DateTimeException e) { // If the date and time inputted are not valid
            return false;
        }
        return true;
    }

    public static LocalDateTime setDateTime(String dateTime) {
        // Split the string into date and time components
        String[] parts = dateTime.split(" ");
        String date = parts[0];
        String time = parts[1];

        // Get the arguments for day, month and year
        String[] dateParts = date.split("/");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        // Get the arguments for time
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(2, 4));

        return LocalDateTime.of(year, month, day, hour, min);
    }

    @Override
    public String toString() {
        int day = dateAndTime.getDayOfMonth();
        int month = dateAndTime.getMonthValue();
        int year = dateAndTime.getYear();

        int hour = dateAndTime.getHour();
        int min = dateAndTime.getMinute();
        return String.format("%d/%d/%d %02d%02d", day, month, year, hour, min);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateTime)) {
            return false;
        }

        DateTime otherDateTime = (DateTime) other;
        return dateAndTime.equals(otherDateTime.dateAndTime);
    }

    @Override
    public int hashCode() {
        return dateAndTime.hashCode();
    }

}
