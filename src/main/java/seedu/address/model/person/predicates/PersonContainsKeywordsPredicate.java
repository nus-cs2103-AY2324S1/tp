package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests if a {@code Person} contains certain keywords in a certain field. Note that the implementing predicates are not
 * required to test the validity of the keywords.
 */
public interface PersonContainsKeywordsPredicate extends Predicate<Person> {

    @Override
    boolean test(Person person);

    @Override
    boolean equals(Object other);

    @Override
    String toString();
}
