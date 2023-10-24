package seedu.address.model.person;

import java.util.function.Predicate;

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


}
