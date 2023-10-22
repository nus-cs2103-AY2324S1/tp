package seedu.address.model.attendance.exceptions;

/**
 * Signals that the operation will result in duplicate Attendance.
 */

public class DuplicateAttendanceException extends RuntimeException {
    public DuplicateAttendanceException() {
        super("Operation would result in duplicate attendances");
    }
}
