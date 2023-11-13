package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests whether a person is unpaid.
 */
public class PaidPredicate implements Predicate<Person> {

    private final boolean paid;
    public PaidPredicate(boolean paid) {
        this.paid = paid;
    }
    @Override
    public boolean test(Person person) {
        return !person.getPaid();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PaidPredicate)) {
            return false;
        }

        PaidPredicate otherPaidPredicate = (PaidPredicate) other;
        return this.paid == otherPaidPredicate.paid;
    }
}
