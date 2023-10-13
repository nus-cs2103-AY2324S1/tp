package seedu.flashlingo.model.flashcard;

import seedu.flashlingo.model.tag.Tag;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents each flashcard
 *
 * @author Nathanael M. Tan
 * @version 1.0
 * @since 1.0
 */
public class FlashCard {
    private final OriginalWord originalWord;
    private final Translation translatedWord;
    private Date whenToReview; // Date the flashcard was needs to be reviewed
    private ProficiencyLevel level; // How many times successfully remembered
    private boolean toDelete = false;

    /**
     * Constructor for Flashcard
     *
     * @param originalWord   The word in the original language
     * @param translatedWord The word in the language you are learning
     * @param whenToReview   The date of when you need to review this word
     * @param level          The level of familiarity with the word
     */
    public FlashCard(OriginalWord originalWord, Translation translatedWord, Date whenToReview, int level) {
        this.level = new ProficiencyLevel(level);
        this.whenToReview = whenToReview;
        this.translatedWord = translatedWord;
        this.originalWord = originalWord;
    }

    public OriginalWord getOriginalWord() {
        return originalWord;
    }

    public Translation getTranslatedWord() {
        return translatedWord;
    }

    public Date getWhenToReview() {
        return whenToReview;
    }

    public ProficiencyLevel getLevel() {
        return level;
    }

    public boolean getToDelete() {
        return toDelete;
    }

    /**
     * Returns true if both flashcards have the same originalWord and translatedWord.
     * This defines a weaker notion of equality between two flashcards.
     */
    public boolean isSameFlashCard(FlashCard otherFlashCard) {
        if (otherFlashCard == this) {
            return true;
        }

        return otherFlashCard != null
            && otherFlashCard.getOriginalWord().equals(getOriginalWord());
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
            this.level.upgradeLevel();
        } else {
            this.level.downgradeLevel();
        }
        toDelete = this.level.toDelete(); // If remembered enough, delete from the list
        // Current date in ms + 1 day per level in ms
        this.whenToReview = new Date(new Date().getTime() + this.level.calculateNextReviewInterval());
    }

    /**
     * Formats Flashcard for writing to textFile
     *
     * @return Tab separated String formatted for writing
     */
    @Override
    public String toString() {
        String sb = originalWord + "\n" + translatedWord + "\n" + whenToReview.toString() + "\n" + level;
        return sb;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FlashCard)) {
            return false;
        }
        FlashCard fc = (FlashCard) obj;
        if (fc.originalWord.equals(this.originalWord) && fc.translatedWord.equals(this.translatedWord)) {
            return true;
        }
        return false;
    }

    public boolean isOverdue() {
        return this.whenToReview.before(new Date());
    }
}
