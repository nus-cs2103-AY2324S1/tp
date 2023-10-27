package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.HashMap;

public class ScoreList {
    private final HashMap<String, Score> scoreList;

    public ScoreList() {
        scoreList = new HashMap<String, Score>();
    }

    // TODO: have a TAG parser that checks if its a interview-related tag
    public void updateScoreList(Tag tag, Score score) {
        scoreList.put(tag.tagName, score);
    }

    public Score getScore(Tag tag) {
        return scoreList.get(tag.tagName);
    }

}
