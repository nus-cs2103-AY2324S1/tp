package seedu.address.pojo;

import java.util.Date;

/**
 * Represents each flashcard
 *
 * @author Nathanael M. Tan
 * @version 1.0
 * @since 1.0
 */
public class FlashCard {
    private final String originalWord;
    private final String translatedWord;
    private Date whenToReview; // Date the flashcard was needs to be reviewed
    private int level; // How many times successfully remembered
    private boolean toDelete = false;

    /**
     * Constructor for Flashcard
     *
     * @param originalWord   The word in the original language
     * @param translatedWord The word in the language you are learning
     * @param whenToReview   The date of when you need to review this word
     * @param level          The level of familiarity with the word
     */
    public FlashCard(String originalWord, String translatedWord, Date whenToReview, int level) {
        this.level = level;
        this.whenToReview = whenToReview;
        this.translatedWord = translatedWord;
        this.originalWord = originalWord;
    }

    /**
     * Will update the whenToRead based on the algorithm and the level
     * If hasRemembered is true, will increment level by one and update whenToRead
     * Else, update whenToRead with original level
     *
     * @param hasRemembered If the user has successfully remembered the translated word
     */
    public void updateLastRead(Boolean hasRemembered) {
        if (hasRemembered) {
            this.level += 1;
        }
        if (level == 5) {
            toDelete = true; // If remembered enough, delete from the list
        }
        // Current date in ms + 1 day per level in ms
        this.whenToReview = new Date(new Date().getTime() + this.level * 86400000L);
    }

    /**
     * Formats Flashcard for writing to textFile
     *
     * @return Tab separated String formatted for writing
     */
    @Override
    public String toString() {
        String sb = originalWord + "\t" + translatedWord + "\t" + whenToReview.toString() + "\t" + level;
        return sb;
    }

    public boolean isOverdue() {
        return this.whenToReview.before(new Date());
    }
}
