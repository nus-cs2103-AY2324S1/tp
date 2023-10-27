package transact.model.person;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import transact.commons.util.ToStringBuilder;
import transact.model.StringContainsKeywordsPredicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final StringContainsKeywordsPredicate predicate;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        predicate = new StringContainsKeywordsPredicate(keywords);
    }

    @Override
    public boolean test(Person person) {
        return predicate.test(person.getName().fullName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
        return Objects.equals(otherNameContainsKeywordsPredicate.predicate, predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("predicate", predicate).toString();
    }
}
