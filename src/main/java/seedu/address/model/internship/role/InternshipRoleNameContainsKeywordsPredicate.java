package seedu.address.model.internship.role;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;


/**
 * Tests that a {@code InternshipRole}'s {@code Name} matches any of the keywords given.
 */
public class InternshipRoleNameContainsKeywordsPredicate implements Predicate<InternshipRole> {
    private final List<String> keywords;

    public InternshipRoleNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(InternshipRole internshipRole) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internshipRole.getName().getText(),
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipRoleNameContainsKeywordsPredicate)) {
            return false;
        }

        InternshipRoleNameContainsKeywordsPredicate otherInternshipRoleNameContainsKeywordsPredicate =
                (InternshipRoleNameContainsKeywordsPredicate) other;
        return keywords.equals(otherInternshipRoleNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
