package seedu.address.model.student.grades;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    @Test
    public void mark_success() {
        Attendance attendance = new Attendance();
        attendance.mark();
        assertTrue(attendance.getIsPresent());
    }

    @Test
    public void unmark_success() {
        Attendance attendance = new Attendance();
        attendance.unmark();
        assertFalse(attendance.getIsPresent());
    }

    @Test
    public void equals() {
        Attendance attendance = new Attendance();
        Attendance presentAttendance = new Attendance();
        presentAttendance.mark();
        Attendance absentAttendance = new Attendance();
        absentAttendance.unmark();

        // same values -> returns true
        assertTrue(attendance.equals(new Attendance()));

        // same object -> returns true
        assertTrue(attendance.equals(attendance));

        // null -> returns false
        assertFalse(attendance.equals(null));

        // different values -> returns false
        assertFalse(presentAttendance.equals(absentAttendance));

        // different values -> returns false
        assertFalse(presentAttendance.equals(attendance));
    }

    @Test
    public void toStringMethod() {
        Attendance presentAttendance = new Attendance();
        presentAttendance.mark();

        // presentAttendance should return Present
        assertEquals("Present", presentAttendance.toString());

        Attendance absentAttendance = new Attendance();

        // absentAttendance should return Absent
        assertEquals("Absent", absentAttendance.toString());


    }
}
