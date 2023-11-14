package seedu.address.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Predicate} all are true.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {

    private final List<Predicate<Person>> predicates;

    public PersonContainsKeywordsPredicate(List<Predicate<Person>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public boolean test(Person person) {
        if (predicates.isEmpty()) {
            return false;
        }
        return predicates.stream()
                .allMatch(predicate -> predicate.test(person));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonContainsKeywordsPredicate)) {
            return false;
        }

        PersonContainsKeywordsPredicate otherPersonContainsKeywordsPredicate =
                (PersonContainsKeywordsPredicate) other;
        return predicates.equals(otherPersonContainsKeywordsPredicate.predicates);
    }
}
