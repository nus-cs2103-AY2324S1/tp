package seedu.address.model.person;

import java.time.LocalDate;

/**
 * Represents a Student's attendance in the address book.
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS = "Attendance should only be 0 or 1, where 0 indicates student is "
            + "absent and 1 indicates student is present";
    private final LocalDate date;
    private boolean isPresent;
    private final String moduleName;

    /**
     * Constructs an {@code Attendance}.
     *
     * @param date A valid date.
     * @param isPresent A valid attendance status.
     * @param moduleName A valid module name.
     */
    public Attendance(LocalDate date, boolean isPresent, String moduleName) {
        this.date = date;
        this.isPresent = isPresent;
        this.moduleName = moduleName;
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
     * Returns the module name.
     *
     * @return A String representing the module name.
     */
    public String getModuleName() {
        return moduleName;
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
                && isPresent == otherAttendance.isPresent
                && moduleName.equals(otherAttendance.moduleName);
    }

    @Override
    public int hashCode() {
        return date.hashCode() + (isPresent ? 1 : 0) + moduleName.hashCode();
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Module: " + moduleName + ", Present: " + isPresent;
    }
}
