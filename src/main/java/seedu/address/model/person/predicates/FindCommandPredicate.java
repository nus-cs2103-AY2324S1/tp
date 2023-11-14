package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;


/**
 * Tests that a {@code Person} matches the predicate for FindCommand.
 */
public interface FindCommandPredicate extends Predicate<Person> {

    String toFilterString();
}
