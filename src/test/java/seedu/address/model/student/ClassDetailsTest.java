package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.student.grades.AssignmentTracker;
import seedu.address.model.student.grades.AttendanceTracker;
import seedu.address.model.student.grades.ClassParticipationTracker;

public class ClassDetailsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassDetails(null));
        assertThrows(NullPointerException.class, () -> new ClassDetails(null, null, null, null));
        assertThrows(NullPointerException.class, () -> new ClassDetails("T11",
                new AttendanceTracker(1), null, null));
        assertThrows(NullPointerException.class, () -> new ClassDetails("T11",
                new AttendanceTracker(1),
                new AssignmentTracker(1), null));
    }

    @Test
    public void constructor_invalidClassDetails_throwsIllegalArgumentException() {
        String invalidClassDetails = "11";
        assertThrows(IllegalArgumentException.class, () -> new ClassDetails(invalidClassDetails));
        assertThrows(IllegalArgumentException.class, () -> new ClassDetails(invalidClassDetails,
                new AttendanceTracker(1),
                new AssignmentTracker(1),
                new ClassParticipationTracker(1)));
    }

    @Test
    public void constructor_validClassDetails_success() {
        String invalidClassDetails = "T11";
        new ClassDetails(invalidClassDetails);
        new ClassDetails(invalidClassDetails,
                new AttendanceTracker(1),
                new AssignmentTracker(1),
                new ClassParticipationTracker(1));
    }

    @Test
    public void isValidClassDetails() {
        // null class number
        assertThrows(NullPointerException.class, () -> ClassDetails.isValidClassDetails(null));

        // invalid class number
        assertFalse(ClassDetails.isValidClassDetails("")); // empty string
        assertFalse(ClassDetails.isValidClassDetails("11")); // doesn't start with T

        // valid class numbers
        assertTrue(ClassDetails.isValidClassDetails("T01"));
        assertTrue(ClassDetails.isValidClassDetails("T11"));
        assertTrue(ClassDetails.isValidClassDetails("T02"));
    }

    @Test
    public void setAssignmentGrade_invalidValues_exceptionThrown() {
        ClassDetails classDetails = new ClassDetails("T11");
        ClassDetails.setAssignmentCount(3);
        assertThrows(CommandException.class, () -> classDetails.setAssignGrade(4, 0));
        assertThrows(CommandException.class, () -> classDetails.setAssignGrade(-1, 0));
        assertThrows(CommandException.class, () -> classDetails.setAssignGrade(1, -1));
        assertThrows(CommandException.class, () -> classDetails.setAssignGrade(1, 200));
    }

    @Test
    public void setAssignmentGrade_validValues_success() {
        ClassDetails classDetails = new ClassDetails("T11");
        ClassDetails.setAssignmentCount(3);
        try {
            classDetails.setAssignGrade(3, 0);
            classDetails.setAssignGrade(1, 100);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void recordClassPart_invalidValues_exceptionThrown() {
        ClassDetails classDetails = new ClassDetails("T11");
        ClassDetails.setTutorialCount(10);
        assertThrows(CommandException.class, () -> classDetails.recordClassPart(11, true));
        assertThrows(CommandException.class, () -> classDetails.recordClassPart(-1, false));
    }

    @Test
    public void recordClassPart_validValues_success() {
        ClassDetails classDetails = new ClassDetails("T11");
        ClassDetails.setTutorialCount(10);
        try {
            classDetails.recordClassPart(1, true);
            classDetails.recordClassPart(10, false);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void equals() {
        ClassDetails classDetails = new ClassDetails("T11");

        // same values -> returns true
        assertTrue(classDetails.equals(new ClassDetails("T11")));

        // same object -> returns true
        assertTrue(classDetails.equals(classDetails));

        // null -> returns false
        assertFalse(classDetails.equals(null));

        // different types -> returns false
        assertFalse(classDetails.equals(5.0f));

        // different values -> returns false
        assertFalse(classDetails.equals(new ClassDetails("T12")));
    }

    @Test
    public void test_hashCode() {
        ClassDetails classDetails = new ClassDetails("T11");

        // same values -> returns true
        assertTrue(classDetails.hashCode() == (new ClassDetails("T11")).hashCode());

        // same object -> returns true
        assertTrue(classDetails.hashCode() == classDetails.hashCode());

        // null -> returns false
        assertFalse(classDetails.hashCode() == 0);

        // different types -> returns false
        assertFalse(classDetails.hashCode() == (5.0f));

        // different values -> returns false
        assertFalse(classDetails.hashCode() == (new ClassDetails("T12")).hashCode());
    }
}
