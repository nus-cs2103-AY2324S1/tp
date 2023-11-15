package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class CourseContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructor for CourseContainsKeywordsPredicate
     *
     * @param keywords course
     */
    public CourseContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getCourses().stream()
                        .anyMatch(course -> course.toString().contains(keyword)));

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
