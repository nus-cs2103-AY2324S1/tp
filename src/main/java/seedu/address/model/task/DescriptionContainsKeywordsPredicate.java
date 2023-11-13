package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getDescription().fullDescription, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DescriptionContainsKeywordsPredicate)) {
            return false;
        }

        DescriptionContainsKeywordsPredicate otherDescContainsKeywordsPredicate =
                (DescriptionContainsKeywordsPredicate) other;
        return keywords.equals(otherDescContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
