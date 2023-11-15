package seedu.address.model.course.changes;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.course.Course;

/**
 * Represents a course addition.
 */
public class CourseAddition extends CourseChange {
    public static final String MESSAGE_CONSTRAINTS = "Course addition needs 'add' followed by an alphanumeric string.";
    public static final String COURSE_ADDITION_PREFIX = "add-";
    private static final Pattern COURSE_ADDITION_PATTERN = Pattern.compile("^add-(?<course>[A-Za-z0-9]+)$");
    private static Matcher matcher;
    private final Course courseToAdd;

    /**
     * Constructs a {@code CourseAddition}.
     *
     * @param courseAdditionDescription Description for course addition.
     */
    public CourseAddition(String courseAdditionDescription) {
        requireNonNull(courseAdditionDescription);
        checkArgument(isValidCourseAddition(courseAdditionDescription), MESSAGE_CONSTRAINTS);
        courseChangeDescription = courseAdditionDescription;
        String courseToAddName = matcher.group("course");
        checkArgument(Course.isValidCourseName(courseToAddName), Course.MESSAGE_INVALID_COURSE);
        courseToAdd = new Course(courseToAddName);
    }

    /**
     * Checks whether test string follows the format of a valid course addition.
     * @param test the specified string to check.
     * @return true if the test string follows the format of a valid course addition.
     */
    public static boolean isValidCourseAddition(String test) {
        matcher = COURSE_ADDITION_PATTERN.matcher(test);
        return matcher.matches();
    }

    /**
     * Checks if the description provided contains a valid course.
     * @param description the description to check.
     * @return true if the description provided contains a valid course.
     */
    public static boolean checkIfValidCourse(String description) {
        return CourseChange.checkIfValidCourse(matcher, COURSE_ADDITION_PATTERN, description, "course");
    }

    /**
     * Parses the course name from the given description.
     * @param description the description to parse.
     * @return the parsed course name from the given description.
     */
    public static String getParsedCourseName(String description) {
        return CourseChange.getParsedCourseName(matcher, COURSE_ADDITION_PATTERN, description, "course");
    }

    public Course getCourseToAdd() {
        return courseToAdd;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseAddition)) {
            return false;
        }

        CourseAddition otherCourseAddition = (CourseAddition) other;
        return courseToAdd.equals(otherCourseAddition.courseToAdd);
    }

    @Override
    public int hashCode() {
        return courseToAdd.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[Course to add: " + courseToAdd + "]";
    }
}
