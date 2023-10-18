package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Policy Expiry date} matches any of the keywords given.
 */
public class PolicyExpiryContainsKeywordsPredicate extends FieldPredicates {

    public PolicyExpiryContainsKeywordsPredicate(String keyword) {
        super(keyword);
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        person.getPolicy().getPolicyExpiryDate().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PolicyExpiryContainsKeywordsPredicate)) {
            return false;
        }

        PolicyExpiryContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
                (PolicyExpiryContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }
}
