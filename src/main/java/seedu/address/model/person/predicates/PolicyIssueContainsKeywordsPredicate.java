package seedu.address.model.person.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Policy Issue date} matches any of the keywords given.
 */
public class PolicyIssueContainsKeywordsPredicate extends FieldPredicates {

    public PolicyIssueContainsKeywordsPredicate(String keyword) {
        super(keyword);
    }

    @Override
    public boolean test(Person person) {
        return !person.hasDefaultPolicy() && keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        person.getPolicy().getPolicyIssueDate().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PolicyIssueContainsKeywordsPredicate)) {
            return false;
        }

        PolicyIssueContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
                (PolicyIssueContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }
}
