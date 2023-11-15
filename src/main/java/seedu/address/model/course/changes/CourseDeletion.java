package seedu.address.model.course.changes;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.course.Course;

/**
 * Represents a course deletion.
 */
public class CourseDeletion extends CourseChange {
    public static final String MESSAGE_CONSTRAINTS = "Course deletion needs 'del' followed by an alphanumeric string.";
    private static final Pattern COURSE_DELETION_PATTERN = Pattern.compile("^del-(?<course>[A-Za-z0-9]+)$");
    private static Matcher matcher;
    private final Course courseToDelete;

    /**
     * Constructs a {@code CourseDeletion}.
     *
     * @param courseDeletionDescription Description for course deletion.
     */
    public CourseDeletion(String courseDeletionDescription) {
        requireNonNull(courseDeletionDescription);
        checkArgument(isValidCourseDeletion(courseDeletionDescription), MESSAGE_CONSTRAINTS);
        courseChangeDescription = courseDeletionDescription;
        String courseToDeleteName = matcher.group("course");
        checkArgument(Course.isValidCourseName(courseToDeleteName), Course.MESSAGE_INVALID_COURSE);
        courseToDelete = new Course(courseToDeleteName);
    }

    /**
     * Checks whether test string follows the format of a valid course deletion.
     * @param test the specified string to check.
     * @return true if the test string follows the format of a valid course deletion.
     */
    public static boolean isValidCourseDeletion(String test) {
        matcher = COURSE_DELETION_PATTERN.matcher(test);
        return matcher.matches();
    }

    /**
     * Checks if the description provided contains a valid course.
     * @param description the description to check.
     * @return true if the description provided contains a valid course.
     */
    public static boolean checkIfValidCourse(String description) {
        return CourseChange.checkIfValidCourse(matcher, COURSE_DELETION_PATTERN, description, "course");
    }

    /**
     * Parses the course name from the given description.
     * @param description the description to parse.
     * @return the parsed course name from the given description.
     */
    public static String getParsedCourseName(String description) {
        return CourseChange.getParsedCourseName(matcher, COURSE_DELETION_PATTERN, description, "course");
    }

    public Course getCourseToDelete() {
        return courseToDelete;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseDeletion)) {
            return false;
        }

        CourseDeletion otherCourseDeletion = (CourseDeletion) other;
        return courseToDelete.equals(otherCourseDeletion.courseToDelete);
    }

    @Override
    public int hashCode() {
        return courseToDelete.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[Course to delete: " + courseToDelete + "]";
    }
}
