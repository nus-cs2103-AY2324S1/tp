package seedu.address.model.month;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a month in a Year in the format MM-yyyy.
 */
public class DeleteMonth {
    public static final String VALIDATION_MONTH_FORMAT = "MM-yyyy";

    public static final String MESSAGE_CONSTRAINTS = "delete month should be in the format " + VALIDATION_MONTH_FORMAT;

    public final YearMonth month;

    /**
     * Constructs a {@code DeleteMonth}.
     *
     * @param deleteMonth A valid month with year.
     */
    public DeleteMonth(String deleteMonth) {
        requireNonNull(deleteMonth);
        checkArgument(isValidDeleteMonth(deleteMonth), MESSAGE_CONSTRAINTS);
        this.month = YearMonth.parse(deleteMonth, DateTimeFormatter.ofPattern(VALIDATION_MONTH_FORMAT));
    }

    /**
     * Returns true if a given string is a valid date in the format MM-yyyy.
     */
    public static boolean isValidDeleteMonth(String test) {
        String regex = "^(0[1-9]|1[0-2])-(\\d{4})$"; // MM-yyyy format

        if (test.matches(regex)) {
            return true;
        }
        return false;
    }

    public int getYear() {
        return month.getYear();
    }

    public int getMonth() {
        return month.getMonthValue();
    }

    @Override
    public String toString() {
        return month.format(DateTimeFormatter.ofPattern(VALIDATION_MONTH_FORMAT));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMonth)) {
            return false;
        }

        DeleteMonth otherDeleteMonth = (DeleteMonth) other;
        return month.equals(otherDeleteMonth.month);
    }

    @Override
    public int hashCode() {
        return month.hashCode();
    }
}
