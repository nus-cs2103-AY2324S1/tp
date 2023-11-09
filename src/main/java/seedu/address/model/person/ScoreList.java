package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents the score list of a person.
 */
public class ScoreList {
    private static final String MESSAGE_CONSTRAINTS = "Score tag should start with assessment";
    private static final String MESSAGE_MISSING_TAG = "Tag does not exist in score list";
    private final HashMap<String, Score> scoreList;

    public ScoreList() {
        scoreList = new HashMap<String, Score>();
    }

    public ScoreList(HashMap<String, Score> scoreList) {
        this.scoreList = scoreList;
    }


    /**
     * Updates the score list with the new score.
     * @param tag tag
     * @param score score
     */
    public void updateScoreList(Tag tag, Score score) {
        requireAllNonNull(tag, score);
        isValidScoreTag(tag);
        checkArgument(isValidScoreTag(tag), MESSAGE_CONSTRAINTS);
        checkArgument(Score.isValidScore(score), Score.MESSAGE_CONSTRAINTS);
        scoreList.put(tag.tagName, score);
    }

    /**
     * Returns the score associated with the tag.
     * @param tag tag
     * @return score associated with the tag
     */
    public Score getScore(Tag tag) {
        checkArgument(scoreList.containsKey(tag.tagName), MESSAGE_MISSING_TAG);
        return scoreList.get(tag.tagName);
    }

    /**
     * Removes the score associated with the tag. Does nothing if the tag does not exist.
     * @param tag tag
     */
    public void removeScore(Tag tag) {
        if (hasTag(tag)) {
            scoreList.remove(tag.tagName);
        }
    }

    public HashMap<String, Score> getScoreList() {
        HashMap<String, Score> copy = new HashMap<>();
        for (Map.Entry<String, Score> entry : scoreList.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }

    /**
     * Returns true if the score list contains the tag.
     * @param tag tag
     * @return true if the score list contains the tag
     */
    public boolean hasTag(Tag tag) {
        return scoreList.containsKey(tag.tagName);
    }

    /**
     * Gets the List of tags with score.
     * @return List of tags with score
     */
    public List<Tag> getTagsWithScore() {
        ArrayList<Tag> result = new ArrayList<Tag>();
        if (scoreList.isEmpty()) {
            return result;
        }
        Set<String> tags = scoreList.keySet();

        for (String tag : tags) {
            result.add(new Tag(tag, "assessment"));
        }
        return result;
    }

    /**
     * Returns true if a given tag is a valid score tag.
     * @param tag tag
     * @return true if a given tag is a valid score tag
     */
    public static boolean isValidScoreTag(Tag tag) {
        return tag.tagCategory.toLowerCase().contains("assessment");
    }


    /**
     * Returns true if the score list is empty.
     * @return true if the score list is empty
     */
    public boolean isEmpty() {
        return scoreList.isEmpty();
    }



    /**
     * Returns true if both score lists have the same score list.
     * @param other other score list
     * @return true if both score lists have the same score list
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ScoreList)) {
            return false;
        }
        ScoreList otherScoreList = (ScoreList) other;
        return scoreList.equals(otherScoreList.scoreList);
    }

    /**
     * Returns the string representation of the score list.
     * @return
     */
    @Override
    public String toString() {
        return scoreList.toString();
    }

    /**
     * Returns the hashcode of the score list.
     * @return hashcode of the score list
     */
    @Override
    public int hashCode() {
        return scoreList.hashCode();
    }

}
