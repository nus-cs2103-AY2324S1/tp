package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person} has policy.
 */
public class HasPolicyPredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return !person.hasDefaultPolicy();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HasPolicyPredicate)) {
            return false;
        }

        return true;
    }
}
