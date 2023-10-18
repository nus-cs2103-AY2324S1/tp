package seedu.address.model.student.grades;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.model.student.grades.exceptions.InvalidTutorialIndexException;

/**
 * Represents a Student's AttendanceTracker grades
 * in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAttendanceTracker(int)}
 */
public class AttendanceTracker {

    public static final String MESSAGE_CONSTRAINTS = "Attendance Tracker needs to have positive number of tutorials.";

    private final Attendance[] attendanceList;

    /**
     * Constructs an {@code AttendanceTracker}.
     *
     * @param numOfTut A valid integer for the number of tutorials in a class.
     *
     */
    public AttendanceTracker(int numOfTut) {
        checkArgument(isValidAttendance(numOfTut), MESSAGE_CONSTRAINTS);
        attendanceList = new Attendance[numOfTut];
        Arrays.fill(attendanceList, new Attendance());
    }

    /**
     * Returns true if a given number is a valid attendance
     */
    public static boolean isValidAttendance(int numOfTut) {
        return numOfTut >= 0;
    }

    /**
     * Marks attendanceTracker of student as present.
     *
     * @param tutNum The tutorial number.
     */
    public void markPresent(Index tutNum) throws InvalidTutorialIndexException {
        if (tutNum.getZeroBased() >= attendanceList.length) {
            throw new InvalidTutorialIndexException();
        }
        attendanceList[tutNum.getZeroBased()].mark();
    }

    /**
     * Marks attendanceTracker of student as absent.
     *
     * @param tutNum The tutorial number.
     */
    public void markAbsent(Index tutNum) {
        attendanceList[tutNum.getZeroBased()].unmark();
    }

    /**
     * Returns the total number of tutorials.
     */
    public int getNumOfTut() {
        return this.attendanceList.length;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("Attendance:\n");
        for (int i = 0; i < attendanceList.length; i++) {
            ret.append("Tutorial " + (i + 1) + ": " + attendanceList[i].toString() + "\n");
        }
        return ret.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendanceTracker)) {
            return false;
        }

        AttendanceTracker otherAttendanceTracker = (AttendanceTracker) other;
        return Arrays.equals(attendanceList, otherAttendanceTracker.attendanceList);
    }

    @Override
    public int hashCode() {
        return attendanceList.hashCode();
    }

}
