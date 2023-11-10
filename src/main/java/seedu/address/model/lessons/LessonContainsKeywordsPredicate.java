package seedu.address.model.lessons;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

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
}
