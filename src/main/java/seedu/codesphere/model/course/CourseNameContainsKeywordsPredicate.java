//@@author tiongjjyi
package seedu.codesphere.model.course;

import java.util.List;
import java.util.function.Predicate;

import seedu.codesphere.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Course}'s {@code courseName} matches any of the keywords given.
 */
public class CourseNameContainsKeywordsPredicate implements Predicate<Course> {
    private final List<String> keywords;

    public CourseNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Course course) {
        return keywords.stream()
                .anyMatch(keyword -> course.courseName.fullCourseName.toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseNameContainsKeywordsPredicate)) {
            return false;
        }

        CourseNameContainsKeywordsPredicate otherCourseNameContainsKeywordsPredicate =
                (CourseNameContainsKeywordsPredicate) other;
        return keywords.equals(otherCourseNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
