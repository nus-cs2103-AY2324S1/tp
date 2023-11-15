package seedu.address.model.person.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;


/**
 * Tests that a {@code Person}'s {@code Policy Number} matches any of the keywords given.
 */
public class PolicyNumberContainsKeywordsPredicate extends FieldPredicates {
    public PolicyNumberContainsKeywordsPredicate(String keyword) {
        super(keyword);
    }

    @Override
    public boolean test(Person person) {
        return !person.hasDefaultPolicy() && keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        person.getPolicy().getPolicyNumber().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PolicyNumberContainsKeywordsPredicate)) {
            return false;
        }

        PolicyNumberContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
                (PolicyNumberContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }
}
