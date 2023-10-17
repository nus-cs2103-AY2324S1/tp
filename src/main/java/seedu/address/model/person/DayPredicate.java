package seedu.address.model.person;

import seedu.address.commons.util.ToStringBuilder;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Day} matches the day given.
 */
public class DayPredicate implements Predicate<Person> {
    private final Day day;

    public DayPredicate(Day day) {
        this.day = day;
    }

    @Override
    public boolean test(Person person) {
        return person.getDay().equals(this.day);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DayPredicate)) {
            return false;
        }

        DayPredicate otherDayPredicate = (DayPredicate) other;
        return day.equals(otherDayPredicate.day);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("day", day).toString();
    }
}
