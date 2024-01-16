package seedu.address.storage;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.person.Score;
import seedu.address.model.person.ScoreList;
import seedu.address.model.tag.Tag;


/**
 * Jackson-friendly version of {@link ScoreList}.
 */
public class JsonAdaptedScoreList {
    private Map<String, Integer> scores;

    /**
     * @param scoreList
     */
    public JsonAdaptedScoreList(ScoreList scoreList) {
        scores = new HashMap<>();
        for (Map.Entry<String, Score> entry : scoreList.getScoreList().entrySet()) {
            scores.put(entry.getKey(), entry.getValue().value);
        }
    }

    /**
     * Constructs a {@code JsonAdaptedScoreList} with the given score details.
     * @param scores
     */
    @JsonCreator
    public JsonAdaptedScoreList(Map<String, Integer> scores) {
        this.scores = scores;
    }

    /**
     * Gets the score list.
     * @return score list
     */
    @JsonValue
    public Map<String, Integer> getScoreList() {
        return scores;
    }

    /**
     * Converts this Jackson-friendly adapted score list object into the model's {@code ScoreList} object.
     * @return ScoreList
     */
    public ScoreList toModelType() {
        ScoreList scoreList = new ScoreList();
        if (scores == null) {
            return scoreList;
        }
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            scoreList.updateScoreList(new Tag(entry.getKey(), "assessment"), new Score(entry.getValue()));
        }
        return scoreList;
    }
}
