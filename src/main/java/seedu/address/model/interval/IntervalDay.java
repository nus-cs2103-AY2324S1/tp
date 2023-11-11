package seedu.address.model.interval;

import seedu.address.model.person.Day;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;

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
