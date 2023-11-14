package seedu.codesphere.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.codesphere.logic.parser.Prefix;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.student.SortCriteria;
import seedu.codesphere.model.student.Student;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_COURSE_DISPLAYED_INDEX = "The course index provided is invalid";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d student(s) listed!";
    public static final String MESSAGE_COURSES_LISTED_OVERVIEW = "%1$d course(s) listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_WRONG_STAGE_HOME =
                "This command is for a selected course. Please select a course first!";
    public static final String MESSAGE_WRONG_STAGE_COURSE =
                "This command is for home. Please go back home first!";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code student} for display to the user.
     */
    public static String format(Student student) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(student.getName())
                .append("; Email: ")
                .append(student.getEmail())
                .append("; Tag: ")
                .append(student.getTag());
        return builder.toString();
    }

    /**
     * Formats the {@code course} for display to the user.
     */
    public static String format(Course course) {
        final StringBuilder builder = new StringBuilder();
        builder.append(course.getCourseName());
        return builder.toString();
    }

    /**
     * Formats the {@code sortCriteria} for display to the user.
     */
    public static String format(SortCriteria sortCriteria) {
        return sortCriteria.getField().toString().toLowerCase();
    }
}
