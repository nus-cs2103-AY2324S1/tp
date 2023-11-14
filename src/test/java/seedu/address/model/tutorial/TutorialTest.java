package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TutorialTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tutorial(null));
    }

    @Test
    public void constructor_invalidTutorial_throwsIllegalArgumentException() {
        String invalidTutorial = "00";
        assertThrows(IllegalArgumentException.class, () -> new Tutorial(invalidTutorial));
    }

    @Test
    public void constructor_validTutorial_success() {
        String validTutorial = "05";
        Tutorial tutorial = new Tutorial(validTutorial);
        assertTrue(tutorial.isValidTutorial(validTutorial));
    }

    @Test
    public void isValidTutorial() {
        // null tutorial
        assertThrows(NullPointerException.class, () -> Tutorial.isValidTutorial(null));

        // invalid tutorial numbers
        assertFalse(Tutorial.isValidTutorial("00"));
        assertFalse(Tutorial.isValidTutorial("23"));
        assertFalse(Tutorial.isValidTutorial("invalid"));
        assertFalse(Tutorial.isValidTutorial("1A"));

        // valid tutorial numbers
        assertTrue(Tutorial.isValidTutorial("01"));
        assertTrue(Tutorial.isValidTutorial("12"));
        assertTrue(Tutorial.isValidTutorial("22"));
    }

    @Test
    public void getValue() {
        Tutorial tutorial = new Tutorial("05");
        assertEquals("05", tutorial.getValue());
    }

    @Test
    public void equals() {
        Tutorial tutorial1 = new Tutorial("05");
        Tutorial tutorial2 = new Tutorial("05");
        Tutorial tutorial3 = new Tutorial("01");

        // same object -> returns true
        assertTrue(tutorial1.equals(tutorial1));

        // same values -> returns true
        assertTrue(tutorial1.equals(tutorial2));

        // different types -> returns false
        assertFalse(tutorial1.equals(5));

        // null -> returns false
        assertFalse(tutorial1.equals(null));

        // different values -> returns false
        assertFalse(tutorial1.equals(tutorial3));
    }

    @Test
    public void hashCode_returnsExpectedHashCode() {
        Tutorial tutorial = new Tutorial("05");
        int expectedHashCode = "05".hashCode();
        int actualHashCode = tutorial.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }
}
