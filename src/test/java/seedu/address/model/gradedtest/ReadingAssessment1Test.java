package seedu.address.model.gradedtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ReadingAssessment1Test {

    @Test
    public void validReadingAssessmentConstruction() {
        ReadingAssessment1 score = new ReadingAssessment1("10");
        assertEquals("10", score.toString());
    }

    @Test
    public void invalidReadingAssessmentConstruction() {
        // Test construction with an invalid name
        assertThrows(IllegalArgumentException.class, () -> new ReadingAssessment1("**iloveyou**"));
        assertThrows(IllegalArgumentException.class, () -> new ReadingAssessment1("-10000000000"));
    }


    @Test
    public void testHashCode() {
        ReadingAssessment1 score1 = new ReadingAssessment1("10");
        ReadingAssessment1 score2 = new ReadingAssessment1("10");
        ReadingAssessment1 diffScore = new ReadingAssessment1("10.0");

        assertEquals(score1.hashCode(), score2.hashCode());
        assertNotEquals(score1.hashCode(), diffScore.hashCode());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReadingAssessment1(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ReadingAssessment1(invalidName));
    }

    @Test
    public void isValidReadingAssessment() {
        // null score
        assertThrows(NullPointerException.class, () -> ReadingAssessment1.isValidRaResult(null));

        // invalid score
        assertFalse(ReadingAssessment1.isValidRaResult("")); // empty string
        assertFalse(ReadingAssessment1.isValidRaResult(" ")); // spaces only
        assertFalse(ReadingAssessment1.isValidRaResult("^")); // only non-alphanumeric characters
        assertFalse(ReadingAssessment1.isValidRaResult("peter*")); // contains non-alphanumeric characters
        assertFalse(ReadingAssessment1.isValidRaResult("egsdgsg")); // contains alphabets
        assertFalse(ReadingAssessment1.isValidRaResult("-1")); // contains negative numbers

        // valid score
        assertTrue(ReadingAssessment1.isValidRaResult("-")); // default score
        assertTrue(ReadingAssessment1.isValidRaResult("2103")); // numbers only
        assertTrue(ReadingAssessment1.isValidRaResult("100.0")); // floats
        assertTrue(ReadingAssessment1.isValidRaResult("0")); // 0 value allowed

    }

    @Test
    public void equals() {
        ReadingAssessment1 testScore = new ReadingAssessment1("1");

        // same values -> returns true
        assertTrue(testScore.equals(new ReadingAssessment1("1")));

        // same object -> returns true
        assertTrue(testScore.equals(testScore));

        // null -> returns false
        assertFalse(testScore.equals(null));

        // different types -> returns false
        assertFalse(testScore.equals(5.0f));

        // different values -> returns false
        assertFalse(testScore.equals(new ReadingAssessment1("2")));
    }
}
