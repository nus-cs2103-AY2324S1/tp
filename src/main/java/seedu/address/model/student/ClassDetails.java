package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.student.grades.AssignmentTracker;
import seedu.address.model.student.grades.AttendanceTracker;
import seedu.address.model.student.grades.ClassParticipationTracker;
import seedu.address.storage.JsonAdaptedClassDetails;

/**
 * Represents a Student's Class Number in Class Manager.
 * Guarantees: is valid as declared in {@link #isValidClassDetails(String)}
 */
public class ClassDetails {

    public static final String MESSAGE_CONSTRAINTS = "Class number can take any values, and it should not be blank";
    public static final String MESSAGE_INVALID_GRADE = "Grade should be between 0 and 100";
    public static final String MESSAGE_INVALID_ASSIGNMENT_NUMBER = "Assignment number should "
            + "be between 1 and %s";
    public static final String MESSAGE_INVALID_TUTORIAL_SESSION_NUMBER = "Tutorial session number should "
            + "be between 1 and %s";
    public static final String MESSAGE_UNEQUAL_LENGTH = "The number of tutorial sessions and "
            + "attendance records should be equal.";

    // The class number should start with "T".
    public static final String VALIDATION_REGEX = "T.*";
    public static final int DEFAULT_COUNT = 10;
    private static int tutorialCount = DEFAULT_COUNT;
    private static int assignmentCount = DEFAULT_COUNT;

    public final String classDetails;
    public final AttendanceTracker attendanceTracker;
    public final AssignmentTracker assignmentTracker;
    public final ClassParticipationTracker classParticipationTracker;

    /**
     * Constructs an {@code ClassDetails}.
     *
     * @param classDetails A valid Class Number
     *
     */
    public ClassDetails(String classDetails) {
        requireNonNull(classDetails);
        checkArgument(isValidClassDetails(classDetails), MESSAGE_CONSTRAINTS);
        this.classDetails = classDetails;
        attendanceTracker = new AttendanceTracker(tutorialCount);
        classParticipationTracker = new ClassParticipationTracker(tutorialCount);
        assignmentTracker = new AssignmentTracker(assignmentCount);
    }

    /**
     * Constructs an {@code ClassDetails}, with the given class number, attendance tracker,
     * assignment tracker and class participation tracker.
     */
    public ClassDetails(String classDetails, AttendanceTracker attendanceTracker,
                        AssignmentTracker assignmentTracker, ClassParticipationTracker classParticipationTracker) {
        requireNonNull(classDetails);
        requireNonNull(attendanceTracker);
        requireNonNull(assignmentTracker);
        requireNonNull(classParticipationTracker);
        checkArgument(isValidClassDetails(classDetails), MESSAGE_CONSTRAINTS);
        this.classDetails = classDetails;
        this.attendanceTracker = attendanceTracker;
        this.classParticipationTracker = classParticipationTracker;
        this.assignmentTracker = assignmentTracker;
    }

    /**
     * Marks the specific tutorial as present.
     */
    public ClassDetails markPresent(Index tutNum) {
        updateAssignmentAndTutorialCount();
        this.attendanceTracker.markPresent(tutNum);
        return this;
    }

    /**
     * Returns true if a given string is a valid class number.
     */
    public static boolean isValidClassDetails(String test) {
        return (test.matches(VALIDATION_REGEX));
    }

    public static void setTutorialCount(int tutorialCount) {
        ClassDetails.tutorialCount = tutorialCount;
    }

    public static void setAssignmentCount(int assignmentCount) {
        ClassDetails.assignmentCount = assignmentCount;
    }

    @Override
    public String toString() {
        return classDetails;
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
        return classDetails.equals(otherAddress.classDetails)
                && attendanceTracker.equals(otherAddress.attendanceTracker)
                && classParticipationTracker.equals(otherAddress.classParticipationTracker)
                && assignmentTracker.equals(otherAddress.assignmentTracker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classDetails, attendanceTracker, classParticipationTracker, assignmentTracker);
    }

    /**
     * Sets the grade of the student for a particular assignment number.
     * @param assignmentNumber the assignment number
     * @param grade the grade to be set
     * @throws CommandException if the assignment number or grade is invalid
     */
    public void setAssignGrade(int assignmentNumber, int grade) throws CommandException {
        if (assignmentNumber > assignmentCount || assignmentNumber <= 0) {
            throw new CommandException(
                    String.format(MESSAGE_INVALID_ASSIGNMENT_NUMBER, assignmentCount));
        }
        if (grade < 0 || grade > 100) {
            throw new CommandException(MESSAGE_INVALID_GRADE);
        }
        updateAssignmentAndTutorialCount();
        assignmentTracker.editMarks(Index.fromOneBased(assignmentNumber), grade);
    }

    /**
     * Displays the list of assignments and their grades.
     * @return the display message of the assignments and their grades.
     */
    public String displayAssignments() {
        return assignmentTracker.toString();
    }

    /**
     * Records the class participation of the student for a particular tutorial session.
     */
    public void recordClassPart(int sessionNumber, boolean participated) throws CommandException {
        if (sessionNumber > tutorialCount || sessionNumber <= 0) {
            throw new CommandException(
                    String.format(MESSAGE_INVALID_TUTORIAL_SESSION_NUMBER, tutorialCount));
        }
        updateAssignmentAndTutorialCount();
        classParticipationTracker.markParticipation(Index.fromOneBased(sessionNumber), participated);
    }

    /**
     * Updates the assignment and tutorial count in the attendance tracker,
     * class participation tracker and assignment tracker. Whenever the assignment count
     * or tutorial count is changed.
     */
    private void updateAssignmentAndTutorialCount() {
        attendanceTracker.updateTutorialCountChange(tutorialCount);
        classParticipationTracker.updateTutorialCountChange(tutorialCount);
        assignmentTracker.updateAssignmentCountChange(assignmentCount);
    }

    /**
     * Displays the list of tutorial sessions and their participation status.
     * @return the display message of the tutorial sessions and their participation status.
     */
    public String displayParticipations() {
        return classParticipationTracker.toString();
    }

    public JsonAdaptedClassDetails getJsonAdaptedClassDetails() {
        return new JsonAdaptedClassDetails(classDetails,
                attendanceTracker.getJsonAttendanceTracker(),
                assignmentTracker.getJsonAssignmentTracker(),
                classParticipationTracker.getJsonClassParticipationTracker());
    }
}
