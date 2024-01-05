package seedu.address.model.task;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Task}'s {@code Date} matches any of the keywords given.
 */
public class DateContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public DateContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getDate() != null ? task.getDate()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "", keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateContainsKeywordsPredicate)) {
            return false;
        }

        DateContainsKeywordsPredicate otherContainsKeywordsPredicate =
                (DateContainsKeywordsPredicate) other;
        return keywords.equals(otherContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("date keywords", keywords).toString();
    }
}
