package seedu.address.model.gradedtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PracticalExamTest {

    @Test
    public void validPracticalExamConstruction() {
        PracticalExam score = new PracticalExam("10");
        assertEquals("10", score.toString());
    }

    @Test
    public void invalidPracticalExamConstruction() {
        // Test construction with an invalid name
        assertThrows(IllegalArgumentException.class, () -> new PracticalExam("**iloveyou**"));
        assertThrows(IllegalArgumentException.class, () -> new PracticalExam("-10000000000"));
    }

    @Test
    public void testHashCode() {
        PracticalExam score1 = new PracticalExam("10");
        PracticalExam score2 = new PracticalExam("10");
        PracticalExam diffScore = new PracticalExam("10.0");

        assertEquals(score1.hashCode(), score2.hashCode());
        assertNotEquals(score1.hashCode(), diffScore.hashCode());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PracticalExam(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new PracticalExam(invalidName));
    }

    @Test
    public void isValidMidTerms() {
        // null score
        assertThrows(NullPointerException.class, () -> PracticalExam.isValidPeResult(null));

        // invalid score
        assertFalse(PracticalExam.isValidPeResult("")); // empty string
        assertFalse(PracticalExam.isValidPeResult(" ")); // spaces only
        assertFalse(PracticalExam.isValidPeResult("^")); // only non-alphanumeric characters
        assertFalse(PracticalExam.isValidPeResult("peter*")); // contains non-alphanumeric characters
        assertFalse(PracticalExam.isValidPeResult("egsdgsg")); // contains alphabets
        assertFalse(PracticalExam.isValidPeResult("-1")); // contains negative numbers

        // valid score
        assertTrue(PracticalExam.isValidPeResult("-")); // default score
        assertTrue(PracticalExam.isValidPeResult("2103")); // numbers only
        assertTrue(PracticalExam.isValidPeResult("100.0")); // floats
        assertTrue(PracticalExam.isValidPeResult("0")); // 0 value allowed

    }

    @Test
    public void equals() {
        PracticalExam testScore = new PracticalExam("1");

        // same values -> returns true
        assertTrue(testScore.equals(new PracticalExam("1")));

        // same object -> returns true
        assertTrue(testScore.equals(testScore));

        // null -> returns false
        assertFalse(testScore.equals(null));

        // different types -> returns false
        assertFalse(testScore.equals(5.0f));

        // different values -> returns false
        assertFalse(testScore.equals(new PracticalExam("2")));
    }
}
