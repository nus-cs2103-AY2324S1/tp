package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class KeyMilestoneTest {

    @Test
    void isValidKeyMilestone() {
        assertFalse(KeyMilestone.isValidKeyMilestone("01-01-2022"));
        assertFalse(KeyMilestone.isValidKeyMilestone("01.01.2022"));
        assertFalse(KeyMilestone.isValidKeyMilestone("2022-02-31"));
        assertFalse(KeyMilestone.isValidKeyMilestone("2022-01-29"));
        assertFalse(KeyMilestone.isValidKeyMilestone("2022-03-31"));
        assertFalse(KeyMilestone.isValidKeyMilestone("31/02/2022"));

        assertTrue(KeyMilestone.isValidKeyMilestone("01/01/2022"));
        assertTrue(KeyMilestone.isValidKeyMilestone("31/03/2022"));
        assertTrue(KeyMilestone.isValidKeyMilestone("24/12/2023"));
    }

    @Test
    void equals() {
        KeyMilestone keyMilestone = new KeyMilestone("01/12/2023");

        // same values -> returns true
        assertTrue(keyMilestone.equals(new KeyMilestone("01/12/2023")));

        // same object -> returns true
        assertTrue(keyMilestone.equals(keyMilestone));

        // null -> returns false
        assertFalse(keyMilestone.equals(null));

        // different types -> returns false
        assertFalse(keyMilestone.equals(5.0f));

        // different values -> returns false
        assertFalse(keyMilestone.equals(new KeyMilestone("01/01/2023")));
    }

    @Test
    void hashCode_equals() {
        assertEquals(new KeyMilestone("01/12/2023").hashCode(),
                     new KeyMilestone("01/12/2023").hashCode());
    }
}
