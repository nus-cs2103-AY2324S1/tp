package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.student.grades.AssignmentTracker;
import seedu.address.model.student.grades.AttendanceTracker;
import seedu.address.model.student.grades.ClassParticipationTracker;

/**
 * Represents a Student's Class Number in Class Manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassDetails(String)}
 */
public class ClassDetails {

    public static final String MESSAGE_CONSTRAINTS = "Class number can take any values, and it should not be blank";

    public static int TUTORIAL_COUNT;
    public static int ASSIGNMENT_COUNT;
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
        attendanceTracker = new AttendanceTracker(TUTORIAL_COUNT);
        classParticipationTracker = new ClassParticipationTracker(TUTORIAL_COUNT);
        assignmentTracker = new AssignmentTracker(ASSIGNMENT_COUNT);
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

}
