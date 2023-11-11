package seedu.address.model.interval;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import seedu.address.model.person.Day;

/**
 * Represents the day in the interval
 * Guarantees: immutable; is valid as declared in {@link #isValidDay(String)}
 */
public class IntervalDay {
    public static final String MESSAGE_CONSTRAINTS =
            "Days should be written using their full names or their first three letters, and it should not be blank";

    public static final String VALIDATION_REGEX =
            "(?i)^(Mon|Monday|Tue|Tuesday|Wed|Wednesday|Thu|Thursday|Fri|Friday|Sat|Saturday|Sun|Sunday)$";

    public final DayOfWeek value;
    public final String stringValue;

    /**
     * Constructs a {@code Day}.
     *
     * @param day A valid day.
     */
    public IntervalDay(String day) {
        requireNonNull(day);
        checkArgument(isValidDay(day), MESSAGE_CONSTRAINTS);
        value = Day.parse(day);
        stringValue = value.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    }

    /**
     * Parses the day input
     * @param input string of Day
     * @return parses the day into the complete day name
     */
    public String parseDay(String input) {
        String day = input.toLowerCase();
        String result = "";
        switch (day) {
        case "mon":
        case "monday":
            result = "Mon";
            break;
        case "tue":
        case "tuesday":
            result = "Tue";
            break;
        case "wed":
        case "wednesday":
            result = "Wed";
            break;
        case "thu":
        case "thursday":
            result = "Thu";
            break;
        case "fri":
        case "friday":
            result = "Fri";
            break;
        case "sat":
        case "saturday":
            result = "Sat";
            break;
        case "sun":
        case "sunday":
            result = "Sun";
            break;
        default:
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        return result;
    }

    public static boolean isValidDay(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return stringValue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.interval.IntervalDay // instanceof handles nulls
                && value.equals(((seedu.address.model.interval.IntervalDay) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
