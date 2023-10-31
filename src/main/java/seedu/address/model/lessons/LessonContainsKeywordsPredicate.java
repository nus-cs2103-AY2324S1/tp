package seedu.address.model.lessons;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Lesson}'s {@code Name} matches any of the keywords given.
 */
public class LessonContainsKeywordsPredicate implements Predicate<Lesson> {
    private final String keyword;

    public LessonContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Lesson lesson) {
        return StringUtil.containsWordIgnoreCase(lesson.getLessonNameStr(), keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LessonContainsKeywordsPredicate)) {
            return false;
        }

        LessonContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (LessonContainsKeywordsPredicate) other;
        return keyword.equals(otherNameContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
