package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_G01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_T09;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.week.Week;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(AMY.isSamePerson(AMY));

        // null -> returns false
        assertFalse(AMY.isSamePerson(null));

        // same id, all other attributes different -> returns true
        Person editedAmy =
                new PersonBuilder(AMY).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_G01).build();
        assertTrue(AMY.isSamePerson(editedAmy));

        // different id, all other attributes same -> returns false
        editedAmy = new PersonBuilder(AMY).withId(VALID_ID_BOB).build();
        assertFalse(AMY.isSamePerson(editedAmy));
    }

    /**
     * Tests if an empty Optional is returned when there's no attendance record for the current week.
     */
    @Test
    public void getAttendanceForCurrentWeek_noAttendance_emptyOptional() {
        Person emptyBob = new PersonBuilder(BOB).build();
        Optional<Attendance> result = emptyBob.getAttendanceForSpecifiedWeek(new Week(1));
        assertFalse(result.isPresent());
    }

    /**
     * Tests if an attendance record for the current week is correctly retrieved when it exists.
     */
    @Test
    public void getAttendanceForCurrentWeek_attendanceExists_optionalWithAttendance() {
        Week testWeek = new Week(1);
        Attendance attendance = new Attendance(testWeek, true, null);
        Person emptyAmy = new PersonBuilder(AMY).build();
        emptyAmy.addAttendance(attendance);
        Optional<Attendance> result = emptyAmy.getAttendanceForSpecifiedWeek(testWeek);
        assertTrue(result.isPresent());
        assertTrue(result.get().getWeek().equals(testWeek));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person amyCopy = new PersonBuilder(AMY).build();
        assertTrue(AMY.equals(amyCopy));

        // same object -> returns true
        assertTrue(AMY.equals(AMY));

        // null -> returns false
        assertFalse(AMY.equals(null));

        // different type -> returns false
        assertFalse(AMY.equals(5));

        // different person -> returns false
        assertFalse(AMY.equals(BOB));

        // different name -> returns false
        Person editedAmy = new PersonBuilder(AMY).withName(VALID_NAME_BOB).build();
        assertFalse(AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new PersonBuilder(AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new PersonBuilder(AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new PersonBuilder(AMY).withTags(VALID_TAG_G01).build();
        assertFalse(AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + AMY.getName() + ", phone=" + AMY.getPhone()
                + ", email=" + AMY.getEmail() + ", id=" + AMY.getId() + ", tags=" + AMY.getTags() + "}";
        assertEquals(expected, AMY.toString());
    }

    @Test
    public void mergeAttendanceRecordsMethod_noDuplicatedAttendance() {
        Week testWeek1 = new Week(1);
        Attendance attendance1 = new Attendance(testWeek1, true, null);
        List<Attendance> attendanceRecords1 = new ArrayList<>();
        attendanceRecords1.add(attendance1);

        Week testWeek2 = new Week(2);
        Attendance attendance2 = new Attendance(testWeek2, true, null);
        List<Attendance> attendanceRecords2 = new ArrayList<>();
        attendanceRecords2.add(attendance2);

        Person emptyBob = new PersonBuilder(BOB).build();
        emptyBob.mergeAttendanceRecords(attendanceRecords1, attendanceRecords2, emptyBob);

        List<Attendance> expectedRecords = new ArrayList<>();
        expectedRecords.add(attendance1);
        expectedRecords.add(attendance2);

        assertEquals(expectedRecords, emptyBob.getAttendanceRecords());
    }

    @Test
    public void mergeAttendanceRecordsMethod_withDuplicatedAttendance() {
        Week testWeek1 = new Week(1);
        Attendance attendance1 = new Attendance(testWeek1, true, null);
        List<Attendance> attendanceRecords1 = new ArrayList<>();
        attendanceRecords1.add(attendance1);

        Week testWeek2 = new Week(1);
        Attendance attendance2 = new Attendance(testWeek2, true, null);
        List<Attendance> attendanceRecords2 = new ArrayList<>();
        attendanceRecords2.add(attendance2);

        Person emptyAmy = new PersonBuilder().build();
        emptyAmy.mergeAttendanceRecords(attendanceRecords1, attendanceRecords2, emptyAmy);

        List<Attendance> expectedRecords = new ArrayList<>();
        expectedRecords.add(attendance1);

        assertEquals(expectedRecords, emptyAmy.getAttendanceRecords());
    }

    @Test
    public void mergePersonsMethod() {
        Person primaryStudent = new PersonBuilder().withTags(VALID_TAG_G01).build();
        Person secondaryStudent = new PersonBuilder().withTags(VALID_TAG_T09).build();
        Person mergedStudent = primaryStudent.mergePersons(secondaryStudent);

        Person expectedMergedStudent = new PersonBuilder().withTags(VALID_TAG_G01, VALID_TAG_T09).build();

        assertEquals(expectedMergedStudent, mergedStudent);
    }

}
