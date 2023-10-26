package seedu.address.model.person.predicates;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements FindCommandPredicate {
    private final List<String> names;

    public NameContainsKeywordsPredicate(List<String> names) {
        this.names = names;
    }

    @Override
    public String toFilterString() {
        return "name: [" + String.join(", ", names) + "]";
    }

    @Override
    public boolean test(Person person) {
        return names.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
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
        return names.equals(otherNameContainsKeywordsPredicate.names);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("names", names).toString();
    }
}
