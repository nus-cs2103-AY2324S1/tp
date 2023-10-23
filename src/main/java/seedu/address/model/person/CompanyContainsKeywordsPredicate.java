package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Company} matches any of the keywords given.
 */
public class CompanyContainsKeywordsPredicate extends FieldPredicates {
    public CompanyContainsKeywordsPredicate(String keyword) {
        super(keyword);
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(person.getPolicy().getCompany().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyContainsKeywordsPredicate)) {
            return false;
        }

        CompanyContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
                (CompanyContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }
}

}
