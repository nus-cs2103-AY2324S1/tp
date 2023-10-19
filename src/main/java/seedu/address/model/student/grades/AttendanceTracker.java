package seedu.address.model.student.grades;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import seedu.address.commons.core.index.Index;

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
        IntStream.range(0, numOfTut).forEach(i -> attendanceList[i] = new Attendance());
    }

    /**
     * Constructs an {@code AttendanceTracker}. With a given attendance tracker list.
     *
     * @param attendanceTracker A list of booleans to represent attendance.
     */
    public AttendanceTracker(List<Boolean> attendanceTracker) {
        requireNonNull(attendanceTracker);
        attendanceList = new Attendance[attendanceTracker.size()];
        IntStream.range(0, attendanceTracker.size())
                .forEach(i -> attendanceList[i] = new Attendance(attendanceTracker.get(i)));
    }

    /**
     * Returns true if a given number is a valid attendance
     */
    public static boolean isValidAttendance(int numOfTut) {
        return numOfTut >= 0;
    }

    /**
     * Marks attendanceTracker of a student as present.
     *
     * @param tutNum The tutorial number.
     */
    public void markPresent(Index tutNum) {
        attendanceList[tutNum.getZeroBased()].mark();
    }

    /**
     * Marks attendanceTracker of a student as absent.
     *
     * @param tutNum The tutorial number.
     */
    public void markAbsent(Index tutNum) {
        attendanceList[tutNum.getZeroBased()].unmark();
    }

    /**
     * Returns a Json friendly version of the attendanceTracker.
     */
    public List<Boolean> getJsonAttendanceTracker() {
        List<Boolean> attendanceTracker = new ArrayList<>();
        for (Attendance attendance : this.attendanceList) {
            attendanceTracker.add(attendance.getIsPresent());
        }
        return attendanceTracker;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("Attendance:\n");
        for (int i = 0; i < attendanceList.length; i++) {
            ret.append("Tutorial ").append(i + 1).append(": ").append(attendanceList[i].toString()).append("\n");
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
        return Arrays.hashCode(attendanceList);
    }
}
