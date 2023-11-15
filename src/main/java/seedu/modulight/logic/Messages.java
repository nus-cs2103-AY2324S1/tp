package seedu.modulight.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.modulight.logic.parser.Prefix;
import seedu.modulight.model.gradedcomponent.GradedComponent;
import seedu.modulight.model.student.Student;
import seedu.modulight.model.studentscore.StudentScore;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_TOTAL_LISTED_OVERVIEW =
            "%1$d students, %2$d scores, %3$d graded components listed!";
    public static final String MESSAGE_SCORE_LISTED_OVERVIEW = "%1$d scores listed!";

    public static final String MESSAGE_STUDENTS_SORTED_OVERVIEW = "%1$d students sorted!";
    public static final String MESSAGE_SCORES_SORTED_OVERVIEW = "%1$d scores sorted!";
    public static final String MESSAGE_COMP_LISTED_OVERVIEW = "%1$d graded components listed!";

    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

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
    public static String formatStudent(Student student) {
        final StringBuilder builder = new StringBuilder();
        builder.append(student.getStudentId())
                .append("; Name: ")
                .append(student.getName())
                .append("; Email: ")
                .append(student.getEmail())
                .append("; Tutorial Group: ")
                .append(student.getTutorial());
        return builder.toString();
    }

    /**
     * Formats the {@code graded component} for display to the user.
     */
    public static String formatGradedComponent(GradedComponent gc) {
        final StringBuilder builder = new StringBuilder();
        builder.append(gc.getName())
                .append("; Maximum Marks: ")
                .append(gc.getMaxMarks())
                .append("; Weightage: ")
                .append(gc.getWeightage());

        return builder.toString();
    }

    /**
     * Formats the {@code student score} for display to the user.
     */
    public static String formatStudentScore(StudentScore sc) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Student ID: ")
                .append(sc.getStudentId())
                .append("; Graded component name: ")
                .append(sc.getGcName())
                .append("; Score: ")
                .append(sc.getScore());

        return builder.toString();
    }

}
