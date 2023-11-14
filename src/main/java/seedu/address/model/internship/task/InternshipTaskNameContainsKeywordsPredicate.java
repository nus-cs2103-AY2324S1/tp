package seedu.address.model.internship.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;


/**
 * Tests that a {@code InternshipTask}'s {@code Name} matches any of the keywords given.
 */
public class InternshipTaskNameContainsKeywordsPredicate implements Predicate<InternshipTask> {
    private final List<String> keywords;

    public InternshipTaskNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(InternshipTask internshipTask) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internshipTask.getTaskName().getText(),
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipTaskNameContainsKeywordsPredicate)) {
            return false;
        }

        InternshipTaskNameContainsKeywordsPredicate otherInternshipTaskNameContainsKeywordsPredicate =
                (InternshipTaskNameContainsKeywordsPredicate) other;
        return keywords.equals(otherInternshipTaskNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
