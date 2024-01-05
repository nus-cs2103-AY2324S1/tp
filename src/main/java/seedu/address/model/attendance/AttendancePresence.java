package seedu.address.model.attendance;

/**
 * Enum representing the presence of students.
 */
public enum AttendancePresence {
    /**
     * AttendancePresence
     */
    PRESENT, ABSENT;

    /**
     * A constant message indicating the constraints for valid attendance presence.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Presence field should only contain either 'present' or 'absent', and it should not be blank";
}
