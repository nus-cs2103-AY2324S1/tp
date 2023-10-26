package seedu.address.model.person.predicates;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.course.Course;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Courses} matches any of the keywords given.
 */
public class TeachingCourseCommandPredicate implements FindCommandPredicate {
    private final List<Course> courses;

    public TeachingCourseCommandPredicate(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toFilterString() {
        return "course: [" + courses.stream().map(Course::getCourseCode).collect(Collectors.joining("")) + "]";
    }

    @Override
    public boolean test(Person person) {
        return courses.stream()
                .anyMatch(predicateCourse -> person.getCourses().stream()
                        .anyMatch(predicateCourse::equals));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TeachingCourseCommandPredicate)) {
            return false;
        }

        TeachingCourseCommandPredicate otherNameContainsKeywordsPredicate = (TeachingCourseCommandPredicate) other;
        return courses.equals(otherNameContainsKeywordsPredicate.courses);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("courses", courses).toString();
    }
}
