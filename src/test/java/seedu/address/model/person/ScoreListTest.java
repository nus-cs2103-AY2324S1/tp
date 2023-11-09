package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;



public class ScoreListTest {

    private static final Tag VALID_TAG = new Tag("Interview", "assessment");

    @Test
    public void constructor_emptyScoreList() {
        ScoreList scoreList = new ScoreList();
        assertTrue(scoreList.isEmpty());
    }

    @Test
    public void updateScoreList_validTagAndScore_success() {
        ScoreList scoreList = new ScoreList();
        Score score = new Score(85);
        scoreList.updateScoreList(VALID_TAG, score);
        assertTrue(scoreList.hasTag(VALID_TAG));
    }

    @Test
    public void updateScoreList_invalidTag() {
        ScoreList scoreList = new ScoreList();
        Tag tag = new Tag("invalidTag", "wrongType");
        Score score = new Score(85);
        assertThrows(IllegalArgumentException.class, () -> scoreList.updateScoreList(tag, score));
    }

    @Test
    public void getScore_validTagAndScore_success() {
        ScoreList scoreList = new ScoreList();
        Score score = new Score(85);
        scoreList.updateScoreList(VALID_TAG, score);
        assertEquals(score, scoreList.getScore(VALID_TAG));
    }

    @Test
    public void removeScore_tagExists_success() {
        ScoreList scoreList = new ScoreList();
        Score score = new Score(85);
        scoreList.updateScoreList(VALID_TAG, score);
        assertTrue(scoreList.hasTag(VALID_TAG));
        scoreList.removeScore(VALID_TAG);
        assertFalse(scoreList.hasTag(VALID_TAG));
    }

    @Test
    public void removeScore_tagDoesNotExists_noEffect() {
        ScoreList scoreList = new ScoreList();
        assertFalse(scoreList.hasTag(VALID_TAG));
        scoreList.removeScore(VALID_TAG);
        assertFalse(scoreList.hasTag(VALID_TAG));
    }

    @Test
    public void equals() {
        ScoreList scoreList1 = new ScoreList();
        ScoreList scoreList2 = new ScoreList();
        ScoreList scoreList3 = new ScoreList();
        Score score = new Score(85);
        scoreList1.updateScoreList(VALID_TAG, score);
        scoreList2.updateScoreList(VALID_TAG, score);
        scoreList3.updateScoreList(VALID_TAG, score);
        scoreList3.removeScore(VALID_TAG);

        assertEquals(scoreList1, scoreList1);

        assertEquals(scoreList1, scoreList2);
        assertEquals(scoreList2, scoreList1);

        assertEquals(scoreList1, scoreList2);

        assertNotEquals(scoreList1, scoreList3);

        // Inequality
        assertNotEquals(scoreList1, null);
    }
}
