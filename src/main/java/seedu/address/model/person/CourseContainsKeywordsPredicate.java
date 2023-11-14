package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Course} matches any of the keywords given.
 */
public class CourseContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public CourseContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return person.getCourses().stream()
                .map(Course::getCourseName)
                .anyMatch(course -> keywords.stream()
                        .anyMatch(keyword -> keyword.equalsIgnoreCase(course)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseContainsKeywordsPredicate)) {
            return false;
        }

        CourseContainsKeywordsPredicate otherCourseContainsKeywordsPredicate = (CourseContainsKeywordsPredicate) other;
        return keywords.equals(otherCourseContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
