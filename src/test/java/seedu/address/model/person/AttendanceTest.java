package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AttendanceTest {
    @Test
    public void constructor_validAttendanceString_success() {
        Attendance attendance = new Attendance("A,P,A,P,A,P,A,P,A,P,A,P", Attendance.ORIGINAL_PART);
        assertEquals(12, attendance.getTotalWeeks());
        assertEquals(6, attendance.getWeeksPresent());
    }

    @Test
    public void isValidWeek_validWeekNumber_returnsTrue() {
        assertTrue(Attendance.isValidTutorial("1"));
        assertTrue(Attendance.isValidTutorial("12"));
    }

    @Test
    public void isValidWeek_invalidWeekNumber_returnsFalse() {
        assertFalse(Attendance.isValidTutorial("0"));
        assertFalse(Attendance.isValidTutorial("13"));
        assertFalse(Attendance.isValidTutorial("invalid"));
    }

    @Test
    public void isValidParticipation_validInput_true() {
        assertTrue(Attendance.isValidParticipation("0"));
        assertTrue(Attendance.isValidParticipation("100"));
    }

    @Test
    public void isValidParticipation_invalidInput_false() {
        assertFalse(Attendance.isValidParticipation("-1"));
        assertFalse(Attendance.isValidParticipation("abc"));
    }

    @Test
    public void isValidStatus_validStatus_true() {
        assertTrue(Attendance.isValidStatus("A"));
        assertTrue(Attendance.isValidStatus("P"));
        assertTrue(Attendance.isValidStatus("VR"));
    }

    @Test
    public void isValidStatus_invalidStatus_false() {
        assertFalse(Attendance.isValidStatus("-"));
        assertFalse(Attendance.isValidStatus(""));
        assertFalse(Attendance.isValidStatus("1"));
        assertFalse(Attendance.isValidStatus("G"));
        assertFalse(Attendance.isValidStatus(" "));
    }

    @Test
    public void markAttendance_validWeek_success() {
        Attendance attendance = new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART);
        attendance.markAttendance(1, "P");
        assertTrue(attendance.isMarkedWeek(1));
    }

    @Test
    public void unmarkAttendance_validWeek_success() {
        Attendance attendance = new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART);
        attendance.unmarkAttendance(1);
        assertFalse(attendance.isMarkedWeek(1));
    }

    @Test
    public void addParticipationPoints_success() {
        Attendance attendance = new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART);
        attendance.inputParticipationPoints(1, 350);
        assertEquals(350, attendance.getParticipationPoints(1));
    }

    @Test
    public void isMarkedWeek_markedWeek_returnsTrue() {
        Attendance attendance = new Attendance("P,U,U,U,U,U,U,U,U,U,U,U", Attendance.ORIGINAL_PART);
        assertTrue(attendance.isMarkedWeek(0));
    }

    @Test
    public void isMarkedWeek_unmarkedWeek_returnsFalse() {
        Attendance attendance = new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART);
        assertFalse(attendance.isMarkedWeek(1));
    }

    @Test
    public void convertToIntegerWeek_validWeekString_success() {
        int week = Attendance.convertToIntegerWeek("5");
        assertEquals(5, week);
    }

    @Test
    public void convertToIntegerWeek_invalidWeekString_throwsException() {
        assertThrows(NumberFormatException.class, () -> Attendance.convertToIntegerWeek("invalid"));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        Attendance attendance = new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART);
        assertTrue(attendance.equals(attendance));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        Attendance attendance1 = new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART);
        Attendance attendance2 = new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART);
        assertTrue(attendance1.equals(attendance2));
    }

    @Test
    public void equals_differentAttendance_returnsFalse() {
        Attendance attendance1 = new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART);
        Attendance attendance2 = new Attendance("P,U,U,U,U,U,U,U,U,U,U,U", Attendance.ORIGINAL_PART);
        assertFalse(attendance1.equals(attendance2));
    }

    @Test
    public void equals_differentParticipation_returnsFalse() {
        Attendance attendance1 = new Attendance("P,U,U,U,U,U,U,U,U,U,U,U", Attendance.ORIGINAL_PART);
        Attendance attendance2 = new Attendance("P,U,U,U,U,U,U,U,U,U,U,U", "300,0,0,0,0,0,0,0,0,0,0,0,0");
        assertFalse(attendance1.equals(attendance2));
    }

    @Test
    public void equals_differentStatus_returnsFalse() {
        Attendance attendance1 = new Attendance("P,U,U,U,U,U,U,U,U,U,U,U", Attendance.ORIGINAL_PART);
        Attendance attendance2 = new Attendance("VR,U,U,U,U,U,U,U,U,U,U,U", Attendance.ORIGINAL_PART);
        assertFalse(attendance1.equals(attendance2));
    }

    @Test
    public void getTotalMarked_hasOneMarkedPresent_returnsOne() {
        Attendance attendance = new Attendance("P,U,U,U,U,U,U,U,U,U,U,U,U", Attendance.ORIGINAL_PART);
        assertEquals(1, attendance.getTotalMarkedTut());
    }

    @Test
    public void getTotalMarked_hasTwoMarked_returnsTwo() {
        Attendance attendance = new Attendance("P,A,U,U,U,U,U,U,U,U,U,U,U", Attendance.ORIGINAL_PART);
        assertEquals(2, attendance.getTotalMarkedTut());
    }

    @Test
    public void getWeeksPresent_hasOnePresent_returnsOne() {
        Attendance attendance = new Attendance("P,U,U,U,U,U,U,U,U,U,U,U,U", Attendance.ORIGINAL_PART);
        assertEquals(1, attendance.getWeeksPresent());
    }

    @Test
    public void getWeeksPresent_hasOnePresentandOneAbsent_returnsOne() {
        Attendance attendance = new Attendance("P,A,U,U,U,U,U,U,U,U,U,U,U", Attendance.ORIGINAL_PART);
        assertEquals(1, attendance.getWeeksPresent());
    }

    @Test
    public void getWeeksPresent_hasOneAbsent_returnsZero() {
        Attendance attendance = new Attendance("A,U,U,U,U,U,U,U,U,U,U,U,U", Attendance.ORIGINAL_PART);
        assertEquals(0, attendance.getWeeksPresent());
    }

    @Test
    public void getTotalPart_hasNoParticipation_returnsZero() {
        Attendance attendance = new Attendance("P,U,U,U,U,U,U,U,U,U,U,U,U", Attendance.ORIGINAL_PART);
        assertEquals(0, attendance.getTotalPart());
    }

    @Test
    public void getTotalPart_hasNoneZeroParticipation_returnsNonZero() {
        Attendance attendance = new Attendance("P,P,P,P,P,U,U,U,U,U,U,U,U",
                "300,300,300,300,300,0,0,0,0,0,0,0");
        assertEquals(1500, attendance.getTotalPart());
    }

    @Test
    public void attendanceConstructor_hasNonPresentStatus_returnsNoPart() {
        Attendance attendance = new Attendance("U,U,U,U,U,U,U,U,U,U,U,U,U",
                "300,300,300,300,300,0,0,0,0,0,0,0");
        assertEquals(0, attendance.getTotalPart());
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        Attendance attendance = new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART);
        assertFalse(attendance.equals(1));
    }
    @Test
    public void hashCodeTest() {
        Attendance attendance = new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART);
        assertEquals(attendance.hashCode(), attendance.hashCode());
    }
}
