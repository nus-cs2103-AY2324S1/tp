package seedu.address.model.person;

import seedu.address.model.week.Week;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a Student's attendance in the address book.
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS = "Attendance should only be 0 or 1, where 0 indicates student is "
            + "absent and 1 indicates student is present";
    private final Week week;
    private boolean isPresent;

    /**
     * Constructs an {@code Attendance}.
     *
     * @param week A week from 0 to 13.
     * @param isPresent A valid attendance status.
     */
    public Attendance(Week week, boolean isPresent) {
        this.week = week;
        this.isPresent = isPresent;
    }

    /**
     * Returns the week of the attendance.
     *
     * @return An int representing the date of the attendance.
     */
    public Week getWeek() {
        return week;
    }

    /**
     * Returns the attendance status.
     *
     * @return A boolean representing the attendance status.
     */
    public boolean isPresent() {
        return isPresent;
    }

    /**
     * Sets the attendance for the person.
     * @param isPresent The attendance record to set.
     */
    public void setAttendance(boolean isPresent) {
        this.isPresent = isPresent;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Attendance)) {
            return false;
        }
        Attendance otherAttendance = (Attendance) other;
        return this.week == otherAttendance.week
                && isPresent == otherAttendance.isPresent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(week, isPresent);
    }

    @Override
    public String toString() {
        return "Week: " + week + ", Present: " + isPresent;
    }
}
