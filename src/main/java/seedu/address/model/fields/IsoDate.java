package seedu.address.model.fields;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Optional;

/**
 * Represents a task's end date.
 */
public class IsoDate extends Date {

    public static final String DATE_FORMAT = "uuuu-MM-dd HH:mm";

    public static final String DATE_FORMAT_WITHOUT_TIME = "uuuu-MM-dd";

    public static final String MESSAGE_CONSTRAINTS = "Enter date in yyyy-mm-dd HH:mm or yyyy-mm-dd format.";

    private static final DateTimeFormatter dfWithTime = DateTimeFormatter.ofPattern(DATE_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);

    private static final DateTimeFormatter dfWithoutTime = DateTimeFormatter.ofPattern(DATE_FORMAT_WITHOUT_TIME)
            .withResolverStyle(ResolverStyle.STRICT);

    public static final String MESSAGE_CONSTRAINTS_LIST =
            MESSAGE_CONSTRAINTS + " Start date must be before end date.";

    private final LocalDateTime endDate;

    /**
     * Constructs a {@code EndDate}.
     *
     * @param endDate A valid date.
     */
    public IsoDate(LocalDateTime endDate) {
        requireNonNull(endDate);
        this.endDate = endDate;
    }

    /**
     * Compares if a given date is after today's date.
     *
     * @param date User input of a date to be verified against today's date.
     * @return True if valid date (ie after today's date). False otherwise.
     */
    public static boolean isValidDateNotBeforeToday(String date) {
        try {
            LocalDateTime d = LocalDateTime.parse(date, dfWithTime);
            return !d.isBefore(LocalDateTime.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Compares if a given start date is before an end date.
     *
     * @param startDate Start Date to be compared against {@param endDate}.
     * @param endDate End Date to be compared against {@param startDate}.
     * @return True if {@param startDate} is before {@param endDate}.
     */
    public static boolean isDateBefore(String startDate, String endDate) {
        try {
            LocalDateTime startDateFilter = LocalDateTime.parse(startDate, dfWithTime);
            LocalDateTime endDateFilter = LocalDateTime.parse(endDate, dfWithTime);
            return startDateFilter.isBefore(endDateFilter);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if a {@code date} is of the format {@code DATE_FORMAT}
     *
     * @param date The date to be checked.
     * @return True if the date is of the format. False otherwise.
     */
    public static boolean isValidIsoDate(String date) {
        try {
            LocalDateTime d = LocalDateTime.parse(date, dfWithTime);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if a {@code date} is of the format {@code DATE_FORMAT_WITHOUT_TIME}
     *
     * @param date The date to be checked.
     * @return True if the date is of the format. False otherwise.
     */
    public static boolean isValidIsoDateWithoutTime(String date) {
        try {
            LocalDate d = LocalDate.parse(date, dfWithoutTime);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * @param date User input of a date without timing to be verified.
     * @return True if valid date (ie after today's date). False otherwise.
     */
    public static boolean isValidIsoDateWithoutTimeAfterCurrent(String date) {
        try {
            LocalDate d = LocalDate.parse(date, dfWithoutTime);
            return !d.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * @param date to be verified
     * @return true if valid date, false otherwise
     */
    public static boolean isValidSavedDate(String date) {
        try {
            LocalDateTime.parse(date, dfWithTime);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String toParseString() {
        return endDate.format(dfWithTime);
    }

    @Override
    public Optional<LocalDateTime> getDate() {
        return Optional.of(endDate);
    }

    @Override
    public String toString() {
        return endDate.format(dfWithTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IsoDate)) {
            return false;
        }

        IsoDate otherEndDate = (IsoDate) other;
        return endDate.equals(otherEndDate.endDate);
    }

    @Override
    public int hashCode() {
        return endDate.hashCode();
    }

    @Override
    public String toSaveData() {
        return endDate.format(dfWithTime);
    }

    @Override
    public int compareTo(Date o) {
        if (o instanceof IsoDate) {
            IsoDate other = (IsoDate) o;
            if (this.endDate.isEqual(other.endDate)) {
                return 0;
            } else if (this.endDate.isAfter(other.endDate)) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }
}
