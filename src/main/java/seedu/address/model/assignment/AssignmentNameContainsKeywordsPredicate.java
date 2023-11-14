package seedu.address.model.assignment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;


/**
 * Tests that a {@code Assignment}'s {@code Name} matches any of the keywords given.
 */
public class AssignmentNameContainsKeywordsPredicate implements Predicate<Assignment> {
    private final List<String> keywords;

    public AssignmentNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Assignment assignment) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(assignment.getName().getText(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignmentNameContainsKeywordsPredicate)) {
            return false;
        }

        AssignmentNameContainsKeywordsPredicate otherAssignmentNameContainsKeywordsPredicate =
                (AssignmentNameContainsKeywordsPredicate) other;
        return keywords.equals(otherAssignmentNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
