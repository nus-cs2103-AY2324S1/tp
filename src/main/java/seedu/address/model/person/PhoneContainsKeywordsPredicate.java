package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Phone Number} matches any of the keywords given.
 */
public class PhoneContainsKeywordsPredicate extends FieldPredicates {

    public PhoneContainsKeywordsPredicate(String keyword) {
        super(keyword);
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PhoneContainsKeywordsPredicate)) {
            return false;
        }

        PhoneContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (PhoneContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }
}
