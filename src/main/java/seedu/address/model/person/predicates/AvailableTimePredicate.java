package seedu.address.model.person.predicates;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.availability.TimeInterval;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code FreeTime} is within the FreeTime that is given.
 */
public class AvailableTimePredicate implements FindCommandPredicate {
    private static final String[] DAY_OF_WEEK = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private final TimeInterval interval;
    private final Integer day;

    /**
     * Constructs a new AvailableTimePredicate with the specified day and time interval.
     *
     * @param day The day for which the predicate applies.
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
        return "\nday: " + AvailableTimePredicate.DAY_OF_WEEK[day - 1]
                + "\nfree time: [" + interval.toString() + "]";
    }

    @Override
    public boolean test(Person person) {
        if (person.getFreeTime() == null) {
            return false;
        }

        TimeInterval timeInterval = person.getFreeTime().getIntervals().get(day - 1);
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

        AvailableTimePredicate otherNameContainsKeywordsPredicate = (AvailableTimePredicate) other;
        return interval.equals(otherNameContainsKeywordsPredicate.interval);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("day", day)
                .add("free time", interval)
                .toString();
    }
}
