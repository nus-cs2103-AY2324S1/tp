package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.week.Week;

/**
 * Test class for the Attendance model.
 */
public class AttendanceTest {

    /**
     * Tests the equality of two Attendance objects.
     */
    @Test
    public void equals() {
        Attendance attendance1 = new Attendance(new Week(1), true, null);
        Attendance attendance2 = new Attendance(new Week(1), true, null);
        Attendance attendance3 = new Attendance(new Week(1), false, null);

        // same object -> returns true
        assertEquals(attendance1, attendance1);

        // same values -> returns true
        assertEquals(attendance1, attendance2);

        // different types -> returns false
        assertNotEquals(1, attendance1);

        // null -> returns false
        assertNotEquals(null, attendance1);

        // different attendance status -> returns false
        assertNotEquals(attendance1, attendance3);
    }

    /**
     * Tests the retrieval of the date from an Attendance object.
     */
    @Test
    public void getDate_validAttendance_dateRetrieved() {
        Week testDate = new Week(1);
        Attendance attendance = new Attendance(testDate, true, null);
        assertEquals(testDate, attendance.getWeek());
    }

    /**
     * Tests the setting of attendance status in an Attendance object.
     */
    @Test
    public void setAttendance_changeAttendanceStatus_attendanceStatusChanged() {
        Attendance attendance = new Attendance(new Week(1), true, null);
        attendance.setAttendance(false);
        assertFalse(attendance.isPresent());
    }

    /**
     * Tests the hash code generation for an Attendance object.
     */
    @Test
    public void hashCode_validAttendance_correctHashCode() {
        Week testDate = new Week(1);
        Attendance attendance1 = new Attendance(testDate, true, null);
        Attendance attendance2 = new Attendance(testDate, true, null);
        assertEquals(attendance1.hashCode(), attendance2.hashCode());

        Attendance attendance3 = new Attendance(testDate, false, "Late");
        assertNotEquals(attendance1.hashCode(), attendance3.hashCode());
    }

    /**
     * Tests the string representation of an Attendance object.
     */
    @Test
    public void toString_validAttendance_correctStringRepresentation() {
        Week testDate = new Week(1);
        Attendance attendance = new Attendance(testDate, true, null);
        String expectedString = "Week: " + testDate + ", Present: true";
        assertEquals(expectedString, attendance.toString());
    }
}
