package seedu.address.model.person.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.JoinDate;


public class AttendanceStorageTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AttendanceStorage(null));
    }

    @Test
    public void getType_null_throwsNullPointerException() {
        assertThrows(
                NullPointerException.class, () -> new AttendanceStorage().getType(null));
    }

    @Test
    public void markAbsent_null_throwsNullPointerException() {
        assertThrows(
                NullPointerException.class, () -> new AttendanceStorage().markAbsent(null));
    }

    @Test
    public void markLate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AttendanceStorage().markLate(null));
    }

    @Test
    public void markPresent_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AttendanceStorage().markPresent(null));
    }

    @Test
    public void getTodayAttendance_validAttendanceStorage() {
        AttendanceStorage attendanceStorage = new AttendanceStorage();
        assertEquals(attendanceStorage.getTodayAttendance(), AttendanceType.PRESENT);
    }

    @Test
    public void getAttendanceReport_validJoinDate_invalidNumOfLeave() {
        JoinDate joinDate = new JoinDate("10/03/2023");
        AttendanceStorage attendanceStorage = new AttendanceStorage();
        assertThrows(IllegalArgumentException.class, () -> attendanceStorage.getAttendanceReport(joinDate, -11));
    }

    @Test
    public void getAttendanceReport_nullJoinDate_validNumOfLeave() {
        AttendanceStorage attendanceStorage = new AttendanceStorage();
        assertThrows(NullPointerException.class, () -> attendanceStorage.getAttendanceReport(null, 5));
    }

    @Test
    public void getValue_emptyAttendanceStorage() {
        AttendanceStorage attendanceStorage = new AttendanceStorage();
        assertTrue(attendanceStorage.getValue().equals(new ArrayList<>()));
    }
}
