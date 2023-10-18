package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.student.grades.AssignmentTracker;
import seedu.address.model.student.grades.AttendanceTracker;
import seedu.address.model.student.grades.ClassParticipationTracker;

/**
 * Represents a Student's Class Number
 * in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassDetails(String)}
 */
public class ClassDetails {

    public static final int TEMP_LENGTH = 10;

    public static final String MESSAGE_CONSTRAINTS = "Class number can take any values, and it should not be blank";
    public static final String MESSAGE_INVALID_GRADE = "Grade should be between 0 and 100";
    public static final String MESSAGE_INVALID_ASSIGNMENT_NUMBER = "Assignment number should "
            + "be between 1 and " + TEMP_LENGTH;

    /*
     * The class number should start with "T".
     */
    public static final String VALIDATION_REGEX = "T.*";

    public final String value;

    public final AttendanceTracker attendanceTracker;
    public final AssignmentTracker assignmentTracker;
    public final ClassParticipationTracker classParticipationTracker;

    /**
     * Constructs an {@code Class Number}.
     *
     * @param classDetails A valid Class Number
     *
     */
    public ClassDetails(String classDetails) {
        requireNonNull(classDetails);
        checkArgument(isValidClassDetails(classDetails), MESSAGE_CONSTRAINTS);
        value = classDetails;
        attendanceTracker = new AttendanceTracker(TEMP_LENGTH);
        classParticipationTracker = new ClassParticipationTracker(TEMP_LENGTH);
        assignmentTracker = new AssignmentTracker(TEMP_LENGTH);
    }

    /**
     * Returns true if a given string is a valid class number.
     */
    public static boolean isValidClassDetails(String test) {
        return (test.matches(VALIDATION_REGEX));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassDetails)) {
            return false;
        }

        ClassDetails otherAddress = (ClassDetails) other;
        return value.equals(otherAddress.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public void setAssignGrade(int assignmentNumber, int grade) throws CommandException {
        if (assignmentNumber > TEMP_LENGTH || assignmentNumber <= 0) {
            throw new CommandException(MESSAGE_INVALID_ASSIGNMENT_NUMBER);
        }
        if (grade < 0 || grade > 100) {
            throw new CommandException(MESSAGE_INVALID_GRADE);
        }
        assignmentTracker.editMarks(Index.fromOneBased(assignmentNumber), grade);
    }

    public String displayAssignments() {
        return assignmentTracker.toString();
    }
}
