package seedu.staffsnap.model.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalApplicants.ALICE;
import static seedu.staffsnap.testutil.TypicalApplicants.BENSON;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.model.interview.Interview;
import seedu.staffsnap.model.interview.Rating;
import seedu.staffsnap.testutil.ApplicantBuilder;

public class ScoreTest {
    @Test
    public void constructor_invalidScore_throwsIllegalArgumentException() {
        // negative total score
        assertThrows(IllegalArgumentException.class, () -> new Score(-1, 0, 0));

        // more than 1dp total score
        assertThrows(IllegalArgumentException.class, () -> new Score(2.222, 1, 0));

        // negative average score
        assertThrows(IllegalArgumentException.class, () -> new Score(0, -1, 0));

        // more than 1dp average score
        assertThrows(IllegalArgumentException.class, () -> new Score(0, 1.11, 1));

        // negative number of ratings
        assertThrows(IllegalArgumentException.class, () -> new Score(0, 0, -1));
    }

    @Test
    public void getNumberOfDecimals() {
        // 1dp
        assertEquals(1, Score.getNumberOfDecimals(1.1));

        // 2dp
        assertEquals(2, Score.getNumberOfDecimals(1.11));
    }

    @Test
    public void isValidScore() {
        // negative total score
        assertFalse(Score.isValidScore(-1, 0, 0));

        // more than 1dp total score
        assertFalse(Score.isValidScore(2.222, 1, 0));

        // negative average score
        assertFalse(Score.isValidScore(0, -1, 0));

        // more than 1dp average score
        assertFalse(Score.isValidScore(0, 1.11, 1));

        // negative number of ratings
        assertFalse(Score.isValidScore(0, 0, -1));
    }

    @Test
    public void updateScore_newInterview() {
        Interview newInterview = new Interview("technical", new Rating("10"));
        Applicant applicant = new ApplicantBuilder(ALICE).build();
        applicant.getScore().updateScoreAfterAdd(newInterview);
        assertEquals(10.0, applicant.getScore().getAverageScore());
        assertEquals(10.0, applicant.getScore().getTotalScore());
        assertEquals(1, applicant.getScore().getNumberOfRatings());
    }

    @Test
    public void updateScore_editInterview() {
        Interview newInterview = new Interview("technical", new Rating("10"));
        Applicant applicant = new ApplicantBuilder(BENSON).build();
        applicant.getScore().updateScoreAfterEdit(applicant.getInterviews().get(0),
                newInterview);
        assertEquals(10.0, applicant.getScore().getAverageScore());
        assertEquals(10.0, applicant.getScore().getTotalScore());
        assertEquals(1, applicant.getScore().getNumberOfRatings());
    }

    @Test
    public void decreaseScore_deleteInterview() {
        Applicant applicant = new ApplicantBuilder(ALICE)
                .withScore(new Score(8.1, 8.1, 1)).build();
        Interview interviewToBeDeleted = new Interview("technical", new Rating("8.1"));
        applicant.getScore().updateScoreAfterDelete(interviewToBeDeleted);
        assertEquals(0.0, ALICE.getScore().getAverageScore());
        assertEquals(0.0, ALICE.getScore().getTotalScore());
        assertEquals(0, ALICE.getScore().getNumberOfRatings());
    }

    @Test
    public void hasRating_noInterviews() {
        assertEquals(false, ALICE.getScore().hasRating());
    }

    @Test
    public void hasRating_validInterviews() {
        assertEquals(true, BENSON.getScore().hasRating());
    }

    @Test
    public void getAverageScore_noInterviews() {
        assertEquals(0.0, ALICE.getScore().getAverageScore());
    }

    @Test
    public void getAverageScore_validInterviews() {
        assertEquals(8.0, BENSON.getScore().getAverageScore());
    }

    @Test
    public void getTotalScore_noInterviews() {
        assertEquals(0.0, ALICE.getScore().getTotalScore());
    }

    @Test
    public void getTotalScore_validInterviews() {
        assertEquals(8.0, BENSON.getScore().getTotalScore());
    }

    @Test
    public void getNumberOfRatings_noInterviews() {
        assertEquals(0, ALICE.getScore().getNumberOfRatings());
    }

    @Test
    public void getNumberOfRatings_validInterviews() {
        assertEquals(1, BENSON.getScore().getNumberOfRatings());
    }

    @Test
    public void toStringMethod() {
        assertEquals("8.0", BENSON.getScore().toString());
    }
}
