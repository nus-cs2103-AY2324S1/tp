package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Licence Plate} matches any of the keywords given.
 */
public class LicenceContainsKeywordsPredicate extends FieldPredicates {
    public LicenceContainsKeywordsPredicate(String keyword) {
        super(keyword);
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getLicencePlate().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LicenceContainsKeywordsPredicate)) {
            return false;
        }

        LicenceContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (LicenceContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }
}
