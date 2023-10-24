package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TalliedAttendanceTest {
    @Test
    public void getTalliedAttendance_noAttendanceRecords() {
        Person person = new PersonBuilder().build();
        String expectedString = "Attendance : No attendance records.";
        String actualString = person.getTalliedAttendance();
        assertEquals(expectedString, actualString);
    }
    @Test
    public void getTalliedAttendance_allAbsent() {
        Person person = new PersonBuilder().build();

        LocalDate testDate = LocalDate.now();
        Attendance attendance1 = new Attendance(testDate, false);
        Attendance attendance2 = new Attendance(testDate, false);

        person.addAttendance(attendance1);
        person.addAttendance(attendance2);

        String expectedString = "Attendance : 0 / 2 (2 tutorials missed)";
        String actualString = person.getTalliedAttendance();
        assertEquals(expectedString, actualString);
    }
    @Test
    public void getTalliedAttendance_allPresent() {
        Person person = new PersonBuilder().build();

        LocalDate testDate = LocalDate.now();
        Attendance attendance1 = new Attendance(testDate, true);
        Attendance attendance2 = new Attendance(testDate, true);

        person.addAttendance(attendance1);
        person.addAttendance(attendance2);

        String expectedString = "Attendance : 2 / 2 (0 tutorials missed)";
        String actualString = person.getTalliedAttendance();
        assertEquals(expectedString, actualString);
    }
    @Test
    public void getTalliedAttendance_mixedAttendanceRecords() {
        Person person = new PersonBuilder().build();

        LocalDate testDate = LocalDate.now();
        Attendance attendance1 = new Attendance(testDate, true);
        Attendance attendance2 = new Attendance(testDate, false);

        person.addAttendance(attendance1);
        person.addAttendance(attendance2);

        String expectedString = "Attendance : 1 / 2 (1 tutorials missed)";
        String actualString = person.getTalliedAttendance();
        assertEquals(expectedString, actualString);
    }
}
