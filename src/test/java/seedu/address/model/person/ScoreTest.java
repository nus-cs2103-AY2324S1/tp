package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    public void constructor_invalidScore_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Score(-1));
    }

    @Test
    public void constructor_validScore_success() {
        new Score(0);
        new Score(1000); // The upper limit of score is positive infinity, but this is jsut to test a large value
        new Score(50);
        assert true;
    }

    @Test
    public void isValidScore() {
        // null score
        assertThrows(NullPointerException.class, () -> Score.isValidScore(null));

        // valid scores
        assertTrue(Score.isValidScore(new Score(0))); // minimum score
        assertTrue(Score.isValidScore(new Score(1000))); // random large score
        assertTrue(Score.isValidScore(new Score(50))); // random score
    }

    @Test
    public void isValidScoreValue() {
        // valid score values
        assertTrue(Score.isValidScoreValue(0)); // minimum score
        assertTrue(Score.isValidScoreValue(1000)); // random large score
        assertTrue(Score.isValidScoreValue(50)); // random score

        // invalid score values
        assertFalse(Score.isValidScoreValue(-1)); // negative score
        assertFalse(Score.isValidScoreValue(-1000)); // random negative score
    }

    @Test
    public void equals() {
        Score score = new Score(50);

        // same values -> returns true
        assertTrue(score.equals(new Score(50)));

        // same object -> returns true
        assertTrue(score.equals(score));

        // null -> returns false
        assertFalse(score.equals(null));

        // different values -> returns false
        assertFalse(score.equals(new Score(100)));
    }

    @Test
    public void compareTo() {
        Score score = new Score(50);
        Score score2 = new Score(100);
        Score score3 = new Score(50);

        // same values -> returns 0
        assertTrue(score.compareTo(score3) == 0);

        // different values -> returns -1
        assertTrue(score.compareTo(score2) == -1);

        // different values -> returns 1
        assertTrue(score2.compareTo(score) == 1);
    }


}
