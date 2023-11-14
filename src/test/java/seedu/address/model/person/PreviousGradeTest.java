package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PreviousGradeTest {

    @Test
    public void isValidPreviousGrade() {
        // invalid PreviousGrades
        assertFalse(PreviousGrade.isValidGrade("D-"));
        assertFalse(PreviousGrade.isValidGrade("lol"));

        // valid PreviousGrades
        assertTrue(PreviousGrade.isValidGrade("A+"));
        assertTrue(PreviousGrade.isValidGrade("B"));
        assertTrue(PreviousGrade.isValidGrade("C"));
    }

    @Test
    public void compareTo() {
        PreviousGrade score1 = new PreviousGrade("A");
        PreviousGrade score2 = new PreviousGrade("A");
        PreviousGrade score3 = new PreviousGrade("D");
        PreviousGrade score4 = new PreviousGrade("A+");

        // score1 and score2 have the same values, so should return 0
        assertEquals(0, score1.compareTo(score2));

        // score2 is equal to score1, so should return 0
        assertEquals(0, score2.compareTo(score1));

        // score1 is less than score3, so should return a negative value
        assertTrue(score1.compareTo(score3) < 0);

        // score1 is greater than score4, so should return a positive value
        assertTrue(score1.compareTo(score4) > 0);

    }
    @Test
    public void equals() {
        PreviousGrade previousGrade = new PreviousGrade("B-");

        // same values -> returns true
        assertTrue(previousGrade.equals(new PreviousGrade("B-")));

        // same object -> returns true
        assertTrue(previousGrade.equals(previousGrade));

        // null -> returns false
        assertFalse(previousGrade.equals(null));

        // different types -> returns false
        assertFalse(previousGrade.equals("Test"));

        // different values -> returns false
        assertFalse(previousGrade.equals(new PreviousGrade("B")));
    }

    @Test
    public void testToString() {
        PreviousGrade score1 = new PreviousGrade("A");
        PreviousGrade score2 = new PreviousGrade("B+");
        PreviousGrade score3 = new PreviousGrade("B");
        PreviousGrade score4 = new PreviousGrade("B-");
        PreviousGrade score5 = new PreviousGrade("C+");
        PreviousGrade score6 = new PreviousGrade("C");
        PreviousGrade score7 = new PreviousGrade("D+");
        PreviousGrade score8 = new PreviousGrade("D");
        PreviousGrade score9 = new PreviousGrade("F");

        assertEquals(score1.toString(), "A");
        assertEquals(score2.toString(), "B+");
        assertEquals(score3.toString(), "B");
        assertEquals(score4.toString(), "B-");
        assertEquals(score5.toString(), "C+");
        assertEquals(score6.toString(), "C");
        assertEquals(score7.toString(), "D+");
        assertEquals(score8.toString(), "D");
        assertEquals(score9.toString(), "F");

    }
}
