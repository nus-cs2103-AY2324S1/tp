package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Task}'s {@code Progress} matches any of the keywords given.
 */
public class TaskProgressContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskProgressContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getProgress().name(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskProgressContainsKeywordsPredicate)) {
            return false;
        }

        TaskProgressContainsKeywordsPredicate otherContainsKeywordsPredicate =
                (TaskProgressContainsKeywordsPredicate) other;
        return keywords.equals(otherContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("progress keywords", keywords).toString();
    }
}
