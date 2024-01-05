package seedu.address.model.gradedtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FinalsTest {

    @Test
    public void validFinalsConstruction() {
        Finals score = new Finals("10");
        assertEquals("10", score.toString());
    }

    @Test
    public void invalidFinalsConstruction() {
        // Test construction with an invalid name
        assertThrows(IllegalArgumentException.class, () -> new Finals("**iloveyou**"));
        assertThrows(IllegalArgumentException.class, () -> new Finals("-10000000000"));
    }

    @Test
    public void testHashCode() {
        Finals score1 = new Finals("10");
        Finals score2 = new Finals("10");
        Finals diffScore = new Finals("10.0");

        assertEquals(score1.hashCode(), score2.hashCode());
        assertNotEquals(score1.hashCode(), diffScore.hashCode());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Finals(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Finals(invalidName));
    }

    @Test
    public void isValidFinals() {
        // null score
        assertThrows(NullPointerException.class, () -> Finals.isValidFinalsResult(null));

        // invalid score
        assertFalse(Finals.isValidFinalsResult("")); // empty string
        assertFalse(Finals.isValidFinalsResult(" ")); // spaces only
        assertFalse(Finals.isValidFinalsResult("^")); // only non-alphanumeric characters
        assertFalse(Finals.isValidFinalsResult("peter*")); // contains non-alphanumeric characters
        assertFalse(Finals.isValidFinalsResult("egsdgsg")); // contains alphabets
        assertFalse(Finals.isValidFinalsResult("-1")); // contains negative numbers

        // valid score
        assertTrue(Finals.isValidFinalsResult("-")); // default score
        assertTrue(Finals.isValidFinalsResult("2103")); // numbers only
        assertTrue(Finals.isValidFinalsResult("100.0")); // floats
        assertTrue(Finals.isValidFinalsResult("0")); // 0 value allowed

    }

    @Test
    public void equals() {
        Finals testScore = new Finals("1");

        // same values -> returns true
        assertTrue(testScore.equals(new Finals("1")));

        // same object -> returns true
        assertTrue(testScore.equals(testScore));

        // null -> returns false
        assertFalse(testScore.equals(null));

        // different types -> returns false
        assertFalse(testScore.equals(5.0f));

        // different values -> returns false
        assertFalse(testScore.equals(new Finals("2")));
    }
}
