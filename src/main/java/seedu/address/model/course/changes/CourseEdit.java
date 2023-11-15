package seedu.address.model.course.changes;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.course.Course;

/**
 * Represents a CourseEdit (original course, new course) in the address book.
 * Guarantees: immutable; names are valid as declared in {@link #isValidCourseEdit(String)}
 */
public class CourseEdit extends CourseChange {

    public static final String MESSAGE_CONSTRAINTS = "Course edit pair should include two alphanumeric courses "
            + "separated by a single -.";
    private static Matcher matcher;
    private static final Pattern COURSE_EDIT_PATTERN = Pattern.compile(
            "^(?<originalCourse>[A-Za-z0-9]+)\\s*-\\s*(?<newCourse>[A-Za-z0-9]+)$");
    private final Course originalCourse;
    private final Course newCourse;

    /**
     * Constructs a {@code CourseEdit}.
     *
     * @param courseEditDescription Description for course edit.
     */
    public CourseEdit(String courseEditDescription) {
        requireNonNull(courseEditDescription);
        checkArgument(isValidCourseEdit(courseEditDescription), MESSAGE_CONSTRAINTS);
        courseChangeDescription = courseEditDescription;
        String originalCourseName = matcher.group("originalCourse");
        String newCourseName = matcher.group("newCourse");
        checkArgument(Course.isValidCourseName(originalCourseName), Course.MESSAGE_INVALID_COURSE);
        checkArgument(Course.isValidCourseName(newCourseName), Course.MESSAGE_INVALID_COURSE);
        originalCourse = new Course(originalCourseName);
        newCourse = new Course(newCourseName);
    }

    /**
     * Checks whether test string follows the format of a valid course edit.
     * @param test the specified string to check.
     * @return true if the test string follows the format of a valid course edit.
     */
    public static boolean isValidCourseEdit(String test) {
        matcher = COURSE_EDIT_PATTERN.matcher(test);
        return matcher.matches();
    }

    /**
     * Checks if the description provided contains a valid original course.
     * @param description the description to check.
     * @return true if the description provided contains a valid original course.
     */
    public static boolean checkIfValidOriginalCourse(String description) {
        return CourseChange.checkIfValidCourse(matcher, COURSE_EDIT_PATTERN, description, "originalCourse");
    }

    /**
     * Checks if the description provided contains a valid new course.
     * @param description the description to check.
     * @return true if the description provided contains a valid new course.
     */
    public static boolean checkIfValidNewCourse(String description) {
        return CourseChange.checkIfValidCourse(matcher, COURSE_EDIT_PATTERN, description, "newCourse");
    }

    /**
     * Parses the original course name from the given description.
     * @param description the description to parse.
     * @return the parsed original course name from the given description.
     */
    public static String getParsedOriginalCourseName(String description) {
        return CourseChange.getParsedCourseName(matcher, COURSE_EDIT_PATTERN, description, "originalCourse");
    }

    /**
     * Parses the new course name from the given description.
     * @param description the description to parse.
     * @return the parsed new course name from the given description.
     */
    public static String getParsedNewCourseName(String description) {
        return CourseChange.getParsedCourseName(matcher, COURSE_EDIT_PATTERN, description, "newCourse");
    }

    public Course getOriginalCourse() {
        return originalCourse;
    }

    public Course getNewCourse() {
        return newCourse;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseEdit)) {
            return false;
        }

        CourseEdit otherCourseEdit = (CourseEdit) other;
        return originalCourse.equals(otherCourseEdit.originalCourse) && newCourse.equals(otherCourseEdit.newCourse);
    }

    @Override
    public int hashCode() {
        return originalCourse.hashCode() + newCourse.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[Original Course: " + originalCourse + ", New Course: " + newCourse + "]";
    }
}
