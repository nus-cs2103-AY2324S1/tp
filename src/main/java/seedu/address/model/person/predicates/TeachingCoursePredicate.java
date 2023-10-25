package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.course.Course;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Courses} matches any of the keywords given.
 */
public class TeachingCoursePredicate implements Predicate<Person> {
    private final List<Course> mods;

    public TeachingCoursePredicate(List<Course> mods) {
        this.mods = mods;
    }

    @Override
    public boolean test(Person person) {
        return mods.stream()
                .anyMatch(predicateCourse -> person.getCourses().stream()
                        .anyMatch(predicateCourse::equals));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TeachingCoursePredicate)) {
            return false;
        }

        TeachingCoursePredicate otherNameContainsKeywordsPredicate = (TeachingCoursePredicate) other;
        return mods.equals(otherNameContainsKeywordsPredicate.mods);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("mods", mods).toString();
    }
}
