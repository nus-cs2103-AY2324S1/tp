package seedu.address.model.person.predicates;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Composite predicate, with a well-defined equals method, for use in comparing Persons.
 */
public class CompositePredicate implements Predicate<Person> {

    private final Set<Predicate<Person>> predicateSet;

    public CompositePredicate() {
        predicateSet = new LinkedHashSet<>();
    }

    @Override
    public boolean test(Person person) {
        Predicate<Person> composite = predicateSet.stream().reduce(new IdentityPredicate(), Predicate::and);
        return composite.test(person);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompositePredicate)) {
            return false;
        }

        CompositePredicate otherCompositePredicate = (CompositePredicate) other;
        return predicateSet.equals(otherCompositePredicate.predicateSet);
    }

    @Override
    public String toString() {
        ToStringBuilder result = new ToStringBuilder(this);
        Iterator<Predicate<Person>> predicates = predicateSet.iterator();
        for (int i = 1; predicates.hasNext(); i++) {
            result = result.add(String.valueOf(i), predicates.next().toString());
        }
        return result.toString();
    }
}
