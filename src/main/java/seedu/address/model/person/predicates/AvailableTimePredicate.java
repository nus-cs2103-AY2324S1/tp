package seedu.address.model.person.predicates;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.availability.FreeTime;
import seedu.address.model.availability.TimeInterval;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code FreeTime} is within the FreeTime that is given.
 */
public class AvailableTimePredicate implements FindCommandPredicate {
    private final TimeInterval interval;
    private final Integer day;

    /**
     * Constructs a new AvailableTimePredicate with the specified day and time interval.
     *
     * @param day      The day for which the predicate applies.
     * @param interval The time interval during which the predicate is valid.
     */
    public AvailableTimePredicate(Integer day, TimeInterval interval) {
        this.interval = interval;
        this.day = day;
    }

    /**
     * Generates a filter string representing the day and free time interval.
     *
     * @return A formatted string describing the day of the week and the free time interval.
     */
    @Override
    public String toFilterString() {
        return "\nday: " + DayOfWeek.of(day).getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                + "\nfree time: [" + interval.toString() + "]";
    }

    @Override
    public boolean test(Person person) {
        if (person.getFreeTime() == FreeTime.EMPTY_FREE_TIME) {
            return false;
        }

        TimeInterval timeInterval = person.getFreeTime().getDay(day);
        if (timeInterval != null) {
            return timeInterval.isInBetween(interval);
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AvailableTimePredicate)) {
            return false;
        }

        AvailableTimePredicate otherAvailableTimePredicate = (AvailableTimePredicate) other;
        return interval.equals(otherAvailableTimePredicate.interval) && day.equals(otherAvailableTimePredicate.day);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("day", day)
                .add("free time", interval)
                .toString();
    }
}
