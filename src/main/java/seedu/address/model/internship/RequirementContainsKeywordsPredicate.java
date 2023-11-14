package seedu.address.model.internship;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Internship}'s {@code Requirement} matches any of the keywords given.
 */
public class RequirementContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> keywords;

    public RequirementContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Internship internship) {
        return keywords.stream()
                .allMatch(keyword -> internship.getRequirements().stream()
                        .anyMatch(requirement ->
                                Arrays.stream(requirement.getRequirementName().split("\\s+"))
                                        .anyMatch(word -> StringUtil.containsWordIgnoreCase(word, keyword))));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RequirementContainsKeywordsPredicate)) {
            return false;
        }

        RequirementContainsKeywordsPredicate otherRequirementContainsKeywordsPredicate =
                (RequirementContainsKeywordsPredicate) other;
        return keywords.equals(otherRequirementContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
