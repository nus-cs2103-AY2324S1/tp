package seedu.address.model.person.predicates;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Default predicate for predicate chaining.
 */
public class IdentityPredicate implements Predicate<Person> {

    public IdentityPredicate() {}

    @Override
    public boolean test(Person person) {
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        return other instanceof IdentityPredicate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("identity", true).toString();
    }
}
