package seedu.address.model.person.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;



public class AttendanceTest {
    @Test
    public void constructorTestNullDateValidAtdTypeThrowsNullPointerException() {
        assertThrows(
                NullPointerException.class, () -> new Attendance(null, AttendanceType.LATE));
    }

    @Test
    public void constructorTestValidDateNullAtdTypeThrowsNullPointerException() {
        assertThrows(
                NullPointerException.class, () -> new Attendance(LocalDate.now(), null));
    }

    @Test
    public void constructorTestNullDateNullAttendanceTypeThrowsNullPointerException() {
        assertThrows(
                NullPointerException.class, () -> new Attendance(null, null));
    }

    @Test
    public void getDateTest() {
        Attendance attendance = new Attendance(LocalDate.now(), AttendanceType.LATE);
        assertEquals(attendance.getDate(), LocalDate.now());
    }

    @Test
    public void getTypeTest() {
        Attendance attendance = new Attendance(LocalDate.now(), AttendanceType.LATE);
        assertEquals(attendance.getType(), AttendanceType.LATE);
    }

    @Test
    public void markAbsentTest() {
        Attendance attendance = new Attendance(LocalDate.now(), AttendanceType.LATE);
        attendance.markAbsent();
        assertEquals(attendance.getType(), AttendanceType.ABSENT);
    }

    @Test
    public void markLateTest() {
        Attendance attendance = new Attendance(LocalDate.now(), AttendanceType.PRESENT);
        attendance.markLate();
        assertEquals(attendance.getType(), AttendanceType.LATE);
    }

}
