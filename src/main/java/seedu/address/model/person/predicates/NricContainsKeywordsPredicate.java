package seedu.address.model.person.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code NRIC} matches any of the keywords given.
 */
public class NricContainsKeywordsPredicate extends FieldPredicates {

    public NricContainsKeywordsPredicate(String keyword) {
        super(keyword);
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getNric().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NricContainsKeywordsPredicate)) {
            return false;
        }

        NricContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NricContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }
}
