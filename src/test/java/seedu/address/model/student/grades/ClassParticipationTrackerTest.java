package seedu.address.model.student.grades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClassParticipationTrackerTest {

    @Test
    public void constructor_invalidNumOfTut_throwsIllegalArgumentException() {
        int invalidNumOfTut = -1;
        assertThrows(IllegalArgumentException.class, () -> new ClassParticipationTracker(invalidNumOfTut));
    }

    @Test
    public void isValidAttendance() {
        // invalid number of tutorials
        assertFalse(ClassParticipationTracker.isValidClassPart(-1)); // -negative number

        // valid number of tutorials
        assertTrue(ClassParticipationTracker.isValidClassPart(1));
        assertTrue(ClassParticipationTracker.isValidClassPart(2));
        assertTrue(ClassParticipationTracker.isValidClassPart(10));
    }

    @Test
    public void updateTutorialCountChange() {
        ClassParticipationTracker classParticipationTracker = new ClassParticipationTracker(3);
        ClassParticipationTracker expectedClassParticipationTracker = new ClassParticipationTracker(1);
        classParticipationTracker.updateTutorialCountChange(1);
        assertEquals(expectedClassParticipationTracker, classParticipationTracker);
        expectedClassParticipationTracker = new ClassParticipationTracker(5);
        classParticipationTracker.updateTutorialCountChange(5);
        assertEquals(expectedClassParticipationTracker, classParticipationTracker);
    }

    @Test
    public void equals() {
        ClassParticipationTracker classParticipationTracker = new ClassParticipationTracker(13);

        // same values -> returns true
        assertTrue(classParticipationTracker.equals(new ClassParticipationTracker(13)));

        // same object -> returns true
        assertTrue(classParticipationTracker.equals(classParticipationTracker));

        // null -> returns false
        assertFalse(classParticipationTracker.equals(null));

        // different values -> returns false
        assertFalse(classParticipationTracker.equals(new ClassParticipationTracker(26)));
    }

    @Test
    public void toStringMethod() {
        ClassParticipationTracker classParticipationTracker = new ClassParticipationTracker(3);

        assertEquals("Class Part Tracker:\n"
                + "Tutorial 1: Did not Participate\n"
                + "Tutorial 2: Did not Participate\n"
                + "Tutorial 3: Did not Participate\n", classParticipationTracker.toString());
    }

    @Test
    public void test_hashCode() {
        ClassParticipationTracker classParticipationTracker = new ClassParticipationTracker(13);

        // same values -> returns true
        assertTrue(classParticipationTracker.hashCode() == new ClassParticipationTracker(13).hashCode());

        // same object -> returns true
        assertTrue(classParticipationTracker.hashCode() == classParticipationTracker.hashCode());

        // null -> returns false
        assertFalse(classParticipationTracker.hashCode() == 0);

        // different values -> returns false
        assertFalse(classParticipationTracker.hashCode() == new ClassParticipationTracker(26).hashCode());
    }
}
