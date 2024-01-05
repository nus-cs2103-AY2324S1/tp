package seedu.address.model.gradedtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ReadingAssessment2Test {

    @Test
    public void validReadingAssessmentConstruction() {
        ReadingAssessment2 score = new ReadingAssessment2("10");
        assertEquals("10", score.toString());
    }

    @Test
    public void invalidReadingAssessmentConstruction() {
        // Test construction with an invalid name
        assertThrows(IllegalArgumentException.class, () -> new ReadingAssessment2("**iloveyou**"));
        assertThrows(IllegalArgumentException.class, () -> new ReadingAssessment2("-10000000000"));
    }

    @Test
    public void testHashCode() {
        ReadingAssessment2 score1 = new ReadingAssessment2("10");
        ReadingAssessment2 score2 = new ReadingAssessment2("10");
        ReadingAssessment2 diffScore = new ReadingAssessment2("10.0");

        assertEquals(score1.hashCode(), score2.hashCode());
        assertNotEquals(score1.hashCode(), diffScore.hashCode());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReadingAssessment2(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ReadingAssessment2(invalidName));
    }

    @Test
    public void isValidReadingAssessment() {
        // null score
        assertThrows(NullPointerException.class, () -> ReadingAssessment2.isValidRaResult(null));

        // invalid score
        assertFalse(ReadingAssessment2.isValidRaResult("")); // empty string
        assertFalse(ReadingAssessment2.isValidRaResult(" ")); // spaces only
        assertFalse(ReadingAssessment2.isValidRaResult("^")); // only non-alphanumeric characters
        assertFalse(ReadingAssessment2.isValidRaResult("peter*")); // contains non-alphanumeric characters
        assertFalse(ReadingAssessment2.isValidRaResult("egsdgsg")); // contains alphabets
        assertFalse(ReadingAssessment2.isValidRaResult("-1")); // contains negative numbers

        // valid score
        assertTrue(ReadingAssessment2.isValidRaResult("-")); // default score
        assertTrue(ReadingAssessment2.isValidRaResult("2103")); // numbers only
        assertTrue(ReadingAssessment2.isValidRaResult("100.999")); // floats
        assertTrue(ReadingAssessment2.isValidRaResult("0")); // 0 value allowed

    }

    @Test
    public void equals() {
        ReadingAssessment2 testScore = new ReadingAssessment2("1");

        // same values -> returns true
        assertTrue(testScore.equals(new ReadingAssessment2("1")));

        // same object -> returns true
        assertTrue(testScore.equals(testScore));

        // null -> returns false
        assertFalse(testScore.equals(null));

        // different types -> returns false
        assertFalse(testScore.equals(5.0f));

        // different values -> returns false
        assertFalse(testScore.equals(new ReadingAssessment2("2")));
    }
}
