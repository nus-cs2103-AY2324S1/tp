package seedu.address.model.person;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a Student's attendance in the address book.
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS = "Attendance should only be 0 or 1, where 0 indicates student is "
            + "absent and 1 indicates student is present";
    private final LocalDate date;
    private boolean isPresent;

    /**
     * Constructs an {@code Attendance}.
     *
     * @param date A valid date.
     * @param isPresent A valid attendance status.
     */
    public Attendance(LocalDate date, boolean isPresent) {
        this.date = date;
        this.isPresent = isPresent;
    }

    /**
     * Returns the date of the attendance.
     *
     * @return A LocalDate representing the date of the attendance.
     */
    public LocalDate getDate() {
        return date;
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
        return date.equals(otherAttendance.date)
                && isPresent == otherAttendance.isPresent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, isPresent);
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Present: " + isPresent;
    }
}
