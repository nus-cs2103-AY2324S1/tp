package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InterviewScoreTest {

    @Test
    public void isValidInterviewScore() {
        // invalid InterviewScores
        assertFalse(InterviewScore.isValidInterviewScore(-1.0));
        assertFalse(InterviewScore.isValidInterviewScore(10.1));

        // valid InterviewScores
        assertTrue(InterviewScore.isValidInterviewScore(0.0));
        assertTrue(InterviewScore.isValidInterviewScore(4.0));
        assertTrue(InterviewScore.isValidInterviewScore(5.0));
    }

    @Test
    public void compareTo() {
        InterviewScore score1 = new InterviewScore(3.2);
        InterviewScore score2 = new InterviewScore(3.2);
        InterviewScore score3 = new InterviewScore(3.3);
        InterviewScore score4 = new InterviewScore(3.1);

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
        InterviewScore interviewScore = new InterviewScore(3.2);

        // same values -> returns true
        assertTrue(interviewScore.equals(new InterviewScore(3.2)));

        // same object -> returns true
        assertTrue(interviewScore.equals(interviewScore));

        // null -> returns false
        assertFalse(interviewScore.equals(null));

        // different types -> returns false
        assertFalse(interviewScore.equals("Test"));

        // different values -> returns false
        assertFalse(interviewScore.equals(new InterviewScore(3.3)));
    }
}
