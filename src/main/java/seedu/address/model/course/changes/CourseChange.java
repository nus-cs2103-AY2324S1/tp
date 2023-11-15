package seedu.address.model.course.changes;

import static java.util.Objects.requireNonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.course.Course;

/**
 * Represents a course change.
 */
public abstract class CourseChange {
    public static final String MESSAGE_CONSTRAINTS = "Course change requires either 'add-[course]', 'del-[course]', "
            + "or '[original_course]-[new_course]'";

    protected String courseChangeDescription;

    /**
     * Creates a course change object based on the description provided.
     * @param courseChangeDescription the description provided.
     * @return the course change object.
     */
    public static CourseChange createCourseChange(String courseChangeDescription) {
        requireNonNull(courseChangeDescription);
        // order of if else is important, due to regex pattern.
        if (CourseAddition.isValidCourseAddition(courseChangeDescription)) {
            return new CourseAddition(courseChangeDescription);
        } else if (CourseDeletion.isValidCourseDeletion(courseChangeDescription)) {
            return new CourseDeletion(courseChangeDescription);
        } else if (CourseEdit.isValidCourseEdit(courseChangeDescription)) {
            return new CourseEdit(courseChangeDescription);
        } else {
            return null;
        }
    }

    /**
     * Checks whether a given description contains valid course(s).
     * @param matcher the matcher object for a particular course change.
     * @param pattern the pattern for a particular course change.
     * @param description the given description.
     * @param matchGroup the group to match in the given description.
     * @return whether a given description contains valid course(s).
     */
    public static boolean checkIfValidCourse(Matcher matcher, Pattern pattern, String description, String matchGroup) {
        matcher = pattern.matcher(description);
        return matcher.find() && Course.isExistingCourseName(matcher.group(matchGroup));
    }

    /**
     * Parses a course name from a given description.
     * @param matcher the matcher object for a particular course change.
     * @param pattern the pattern for a particular course change.
     * @param description the given description.
     * @param matchGroup the group to match in the given description.
     * @return the parsed course name if the given description contains it, else return null.
     */
    public static String getParsedCourseName(Matcher matcher, Pattern pattern, String description, String matchGroup) {
        matcher = pattern.matcher(description);
        return matcher.find() ? matcher.group(matchGroup) : null;
    }

    public String getCourseChangeDescription() {
        return courseChangeDescription;
    }
}
