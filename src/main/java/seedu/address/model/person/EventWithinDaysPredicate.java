package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.Event;

/**
 * Tests that a {@code Person}'s {@code Birthday} is within the specified number of days from today.
 */
public class EventWithinDaysPredicate implements Predicate<Event> {
    private final int days;

    public EventWithinDaysPredicate(int days) {
        this.days = days;
    }

    @Override
    public boolean test(Event event) {
        return event.hasStartDateWithinDays(days);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventWithinDaysPredicate)) {
            return false;
        }

        EventWithinDaysPredicate otherPredicate = (EventWithinDaysPredicate) other;
        return days == otherPredicate.days;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("days", days).toString();
    }
}
