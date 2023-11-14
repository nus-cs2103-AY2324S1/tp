package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Task}'s {@code Tags} matches any of the keywords given.
 */
public class TaskContainsTagsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskContainsTagsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    // Returns true if task's tags contain any of the keywords
    @Override
    public boolean test(Task task) {

        // Convert each tag of the task to its string representation and then concatenate them using spaces.
        // The square brackets encapsulate each tag to ensure distinctiveness when checking against keywords.
        // For example, tags such as [[Homework], [Urgent]] will translate to the string "[Homework] [Urgent]".
        String taskTags = task.getTags()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));

        // Match against the concatenated string of tags.
        // The square brackets ensure that keywords match against full tags rather than partial matches.
        // This way, a keyword like "[Home]" won't match with the tag "[Homework]".
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(taskTags, "[" + keyword + "]"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskContainsTagsPredicate)) {
            return false;
        }

        TaskContainsTagsPredicate otherTaskContainsTagsPredicate = (TaskContainsTagsPredicate) other;
        return keywords.equals(otherTaskContainsTagsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
