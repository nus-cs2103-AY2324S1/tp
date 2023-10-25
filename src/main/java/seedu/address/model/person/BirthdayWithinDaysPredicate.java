package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Birthday} is within the specified number of days from today.
 */
public class BirthdayWithinDaysPredicate implements Predicate<Person> {
    private final int days;

    public BirthdayWithinDaysPredicate(int days) {
        this.days = days;
    }

    @Override
    public boolean test(Person person) {
        return person.hasBirthdayWithinDays(days);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BirthdayWithinDaysPredicate)) {
            return false;
        }

        BirthdayWithinDaysPredicate otherPredicate = (BirthdayWithinDaysPredicate) other;
        return days == otherPredicate.days;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("days", days).toString();
    }

}
