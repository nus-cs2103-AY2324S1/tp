package seedu.classmanager.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.classmanager.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.classmanager.commons.core.index.Index;
import seedu.classmanager.commons.exceptions.IllegalValueException;
import seedu.classmanager.logic.commands.exceptions.CommandException;
import seedu.classmanager.model.student.information.AssignmentTracker;
import seedu.classmanager.model.student.information.AttendanceTracker;
import seedu.classmanager.model.student.information.ClassParticipationTracker;
import seedu.classmanager.storage.JsonAdaptedClassDetails;

/**
 * Represents a Student's Class Number in Class Manager.
 * Guarantees: is valid as declared in {@link #isValidClassDetails(String)}
 */
public class ClassDetails {

    public static final String MESSAGE_CONSTRAINTS = "Class number can take any values, and it should not be blank";
    public static final String MESSAGE_INVALID_GRADE = "Grade should be between 0 and 100";
    public static final String MESSAGE_INVALID_ASSIGNMENT_NUMBER = "Assignment number should "
            + "be between 1 and %s";
    public static final String MESSAGE_INVALID_TUTORIAL_INDEX = "Tutorial index should "
            + "be between 1 and %s";
    public static final String MESSAGE_UNEQUAL_LENGTH = "The number of tutorial sessions and "
            + "attendance records should be equal.";

    // The class number should start with "T".
    public static final String VALIDATION_REGEX = "T.*";
    public static final int DEFAULT_COUNT = 10;
    private static int tutorialCount = DEFAULT_COUNT;
    private static int assignmentCount = DEFAULT_COUNT;

    public final String classNumber;
    public final AttendanceTracker attendanceTracker;
    public final AssignmentTracker assignmentTracker;
    public final ClassParticipationTracker classParticipationTracker;

    /**
     * Constructs an {@code ClassDetails}.
     *
     * @param classNumber A valid Class Number
     *
     */
    public ClassDetails(String classNumber) {
        requireNonNull(classNumber);
        checkArgument(isValidClassDetails(classNumber), MESSAGE_CONSTRAINTS);
        this.classNumber = classNumber;
        attendanceTracker = new AttendanceTracker(tutorialCount);
        classParticipationTracker = new ClassParticipationTracker(tutorialCount);
        assignmentTracker = new AssignmentTracker(assignmentCount);
    }

    /**
     * Constructs an {@code ClassDetails}, with the given class number, attendance tracker,
     * assignment tracker and class participation tracker.
     */
    public ClassDetails(String classNumber, AttendanceTracker attendanceTracker,
                        AssignmentTracker assignmentTracker, ClassParticipationTracker classParticipationTracker) {
        requireNonNull(classNumber);
        requireNonNull(attendanceTracker);
        requireNonNull(assignmentTracker);
        requireNonNull(classParticipationTracker);
        checkArgument(isValidClassDetails(classNumber), MESSAGE_CONSTRAINTS);
        this.classNumber = classNumber;
        this.attendanceTracker = attendanceTracker;
        this.classParticipationTracker = classParticipationTracker;
        this.assignmentTracker = assignmentTracker;
    }

    /**
     * Creates a deep copy of the class details.
     * @return A deep copy of {@Code ClassDetails}.
     */
    public ClassDetails copy() {
        AttendanceTracker newAttendanceTracker = this.attendanceTracker.copy();
        AssignmentTracker newAssignmentTracker = this.assignmentTracker.copy();
        ClassParticipationTracker newClassParticipationTracker = this.classParticipationTracker.copy();
        return new ClassDetails(classNumber, newAttendanceTracker, newAssignmentTracker, newClassParticipationTracker);
    }

    /**
     * Marks the specific tutorial as present.
     */
    public void markPresent(Index tutNum) throws CommandException {
        requireNonNull(tutNum);
        if (tutNum.getOneBased() > tutorialCount || tutNum.getOneBased() <= 0) {
            throw new CommandException(
                    String.format(MESSAGE_INVALID_TUTORIAL_INDEX, tutorialCount));
        }
        updateAssignmentAndTutorialCount();
        this.attendanceTracker.markPresent(tutNum);
    }

    /**
     * Marks the specific tutorial as absent.
     */
    public void markAbsent(Index tutNum) throws CommandException {
        requireNonNull(tutNum);
        if (tutNum.getOneBased() > tutorialCount || tutNum.getOneBased() <= 0) {
            throw new CommandException(
                    String.format(MESSAGE_INVALID_TUTORIAL_INDEX, tutorialCount));
        }
        updateAssignmentAndTutorialCount();
        this.attendanceTracker.markAbsent(tutNum);
    }

    public String getClassNumber() {
        return this.classNumber;
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

    /**
     * Gets the attendance percentage.
     *
     * @return Percentage of attendance.
     * @throws IllegalValueException When there is no attendance tracker.
     */
    public double getAttendancePercentage() {
        assert (attendanceTracker != null);
        return attendanceTracker.getPercentage();
    }

    /**
     * Gets the class participation percentage.
     *
     * @return Percentage of class participation.
     * @throws IllegalValueException When there is no class participation tracker.
     */
    public double getClassParticipationPercentage() {
        assert (classParticipationTracker != null);
        return classParticipationTracker.getPercentage();
    }

    /**
     * Gets the assignment percentage.
     *
     * @return Percentage of overall assignment grades.
     * @throws IllegalValueException When there is no assignment tracker.
     */
    public double getAssignmentPercentage() {
        assert (assignmentTracker != null);
        return assignmentTracker.getPercentage();
    }

    public ClassDetails setClassNumber(String classNumber) {
        return new ClassDetails(classNumber, this.attendanceTracker,
                this.assignmentTracker, this.classParticipationTracker);
    }

    /**
     * Sets the grade of the student for a particular assignment number.
     * @param assignmentNumber the assignment number
     * @param grade the grade to be set
     * @throws CommandException if the assignment number or grade is invalid
     */
    public void setGrade(int assignmentNumber, int grade) throws CommandException {
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
    public void recordClassParticipation(int sessionNumber, boolean participated) throws CommandException {
        if (sessionNumber > tutorialCount || sessionNumber <= 0) {
            throw new CommandException(
                    String.format(MESSAGE_INVALID_TUTORIAL_INDEX, tutorialCount));
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
    public String displayParticipation() {
        return classParticipationTracker.toString();
    }

    public JsonAdaptedClassDetails getJsonAdaptedClassDetails() {
        return new JsonAdaptedClassDetails(classNumber,
                attendanceTracker.getJson(),
                assignmentTracker.getJson(),
                classParticipationTracker.getJson());
    }

    @Override
    public String toString() {
        return classNumber;
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

        ClassDetails otherClassDetails = (ClassDetails) other;
        return classNumber.equals(otherClassDetails.classNumber)
                && attendanceTracker.equals(otherClassDetails.attendanceTracker)
                && classParticipationTracker.equals(otherClassDetails.classParticipationTracker)
                && assignmentTracker.equals(otherClassDetails.assignmentTracker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classNumber, attendanceTracker, classParticipationTracker, assignmentTracker);
    }
}
