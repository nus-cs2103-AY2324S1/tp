package seedu.address.model.person.attendance;

/**
 * Types of attendance
 */
public enum AttendanceType {
    LATE,
    ABSENT,
    PRESENT;
    public static final String MESSAGE_CONSTRAINTS =
            "Attendance type should only contain alphabets. It should either be 'present', 'late', 'absent'";
    public static boolean isValidAttendanceType(String name) {
        name = name.toLowerCase();
        return name.equals("present") || name.equals("absent") || name.equals("late");
    }

}
