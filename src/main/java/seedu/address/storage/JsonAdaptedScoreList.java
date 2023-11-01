package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.model.person.Score;
import seedu.address.model.person.ScoreList;
import seedu.address.model.tag.Tag;

import java.util.HashMap;
import java.util.Map;

public class JsonAdaptedScoreList {
    private Map<String, Integer> scores;

    public JsonAdaptedScoreList() {
        scores = new HashMap<>();
    }

    public JsonAdaptedScoreList(ScoreList scoreList) {
        scores = new HashMap<>();
        for (Map.Entry<String, Score> entry : scoreList.getScoreList().entrySet()) {
            scores.put(entry.getKey(), entry.getValue().value);
        }
    }

    @JsonCreator
    public JsonAdaptedScoreList(Map<String, Integer> scores) {
        this.scores = scores;
    }

    @JsonValue
    public Map<String, Integer> getScoreList() {
        return scores;
    }

    public ScoreList toModelType() {
        ScoreList scoreList = new ScoreList();
        System.out.print(scores);
        if (scores == null) {
            return scoreList;
        }
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            scoreList.updateScoreList(new Tag(entry.getKey()), new Score(entry.getValue()));
        }
        return scoreList;
    }
}
