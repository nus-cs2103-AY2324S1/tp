package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Task}'s {@code Title} or {@code Note} matches any of the keywords given.
 */
public class TaskContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;
    private final TitleContainsKeywordsPredicate titlePredicate;
    private final NoteContainsKeywordsPredicate notePredicate;

    /**
     * Constructs a {@code TaskContainsKeywordsPredicate} with the specified keywords.
     *
     * @param keywords A list of keywords to search for.
     */
    public TaskContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
        this.titlePredicate = new TitleContainsKeywordsPredicate(keywords);
        this.notePredicate = new NoteContainsKeywordsPredicate(keywords);
    }

    @Override
    public boolean test(Task task) {
        return titlePredicate.test(task) || notePredicate.test(task);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskContainsKeywordsPredicate)) {
            return false;
        }

        TaskContainsKeywordsPredicate otherTaskContainsKeywordsPredicate = (TaskContainsKeywordsPredicate) other;
        return keywords.equals(otherTaskContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
