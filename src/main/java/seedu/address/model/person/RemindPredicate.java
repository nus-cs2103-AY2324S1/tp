package seedu.address.model.person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Policy Expiry Date}is within a certain period from the current date.
 */
public class RemindPredicate implements Predicate<Person> {

    private static final int VALID_DAYS_STARTS_FROM = 0;
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
        long dueDays = ChronoUnit.DAYS.between(LocalDate.now(), person.getPolicy().getPolicyExpiryDate().date);
        return dueDays >= VALID_DAYS_STARTS_FROM && dueDays <= days;
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
