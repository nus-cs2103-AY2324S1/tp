package seedu.address.model.person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

import seedu.address.model.policy.PolicyDate;


/**
 * Tests that a {@code Person}'s {@code Policy Expiry Date}is within a certain period from the current date.
 */
public class RemindPredicate implements Predicate<Person> {
    private final int days;

    /**
     * Constructor for RemindPredicate.
     *
     * @param days the number of days to be used for the range to check with.
     */
    public RemindPredicate(int days) {
        assert days >= 0;
        this.days = days;
    }

    @Override
    public boolean test(Person person) {
        return !person.getPolicy().getPolicyExpiryDate().toString().equals(PolicyDate.DEFAULT_VALUE)
                && ChronoUnit.DAYS.between(LocalDate.now(), person.getPolicy().getPolicyExpiryDate().date) <= days;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemindPredicate)) {
            return false;
        }

        RemindPredicate otherNameContainsKeywordsPredicate =
                (RemindPredicate) other;
        return days == otherNameContainsKeywordsPredicate.days;
    }
}
