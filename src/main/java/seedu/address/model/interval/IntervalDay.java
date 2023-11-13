package seedu.address.model.interval;

import seedu.address.model.person.Day;

/**
 * Represents the day in the interval
 * Guarantees: immutable; is valid as declared in {@link #isValidDay(String)}
 */
public class IntervalDay extends Day {

    /**
     * Constructs a {@code Day}.
     *
     * @param day A valid day.
     */
    public IntervalDay(String day) {
        super(day);
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

    /**
     * @return a defensive copy of IntervalDay
     */
    public IntervalDay copy() {
        return new IntervalDay(this.stringValue);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.interval.IntervalDay // instanceof handles nulls
                && value.equals(((seedu.address.model.interval.IntervalDay) other).value)); // state check
    }
}
