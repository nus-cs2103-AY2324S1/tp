package seedu.address.model.person.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

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
        int[] arr = new int[]{-11, 0, 0};
        assertTrue(Arrays.equals(arr, attendanceStorage.getAttendanceReport(joinDate, -11)));
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

    @Test
    public void allValidTests() {
        AttendanceStorage attendanceStorage = new AttendanceStorage();

        // markAbsent -- valid LocalDate input
        attendanceStorage.markAbsent(
                LocalDate.parse("20/04/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        String stringAttendance = attendanceStorage.getValue().get(0);
        assertEquals(stringAttendance, "20/04/2023//absent");


        // markLate -- valid LocalDate input, same date
        attendanceStorage.markLate(
                LocalDate.parse("20/04/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        stringAttendance = attendanceStorage.getValue().get(0);
        assertEquals(stringAttendance, "20/04/2023//late");


        // markPresent -- valid LocalDate input, same date
        attendanceStorage.markPresent(
                LocalDate.parse("20/04/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        int size = attendanceStorage.getValue().size();
        assertEquals(size, 0);

        // markAbsent -- valid LocalDate input
        attendanceStorage.markAbsent(
                LocalDate.parse("20/04/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        stringAttendance = attendanceStorage.getValue().get(0);
        assertEquals(stringAttendance, "20/04/2023//absent");


        // markLate -- valid LocalDate input, different date
        attendanceStorage.markLate(
                LocalDate.parse("21/04/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        stringAttendance = attendanceStorage.getValue().get(1);
        assertEquals(stringAttendance, "21/04/2023//late");


        // markPresent -- valid LocalDate input, different date
        attendanceStorage.markPresent(
                LocalDate.parse("22/04/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        size = attendanceStorage.getValue().size();
        assertEquals(size, 2);

        // getType -- valid LocalDate input
        AttendanceType attendanceType = attendanceStorage.getType(
                LocalDate.parse("21/04/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        assertEquals(attendanceType, AttendanceType.LATE);



    }
}
