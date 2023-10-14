package seedu.flashlingo.model.flashcard;

import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

import java.util.Date;

/**
 * Represents each flashcard
 *
 * @author Nathanael M. Tan
 * @version 1.0
 * @since 1.0
 */
public class FlashCard {
    private final OriginalWord originalWord;
    private final TranslatedWord translatedWord;
    private Date whenToReview; // Date the flashcard was needs to be reviewed
    private ProficiencyLevel level; // How many times successfully remembered
    /**
     * Constructor for Flashcard
     *
     * @param originalWord   The word in the original language
     * @param translatedWord The word in the language you are learning
     * @param whenToReview   The date of when you need to review this word
     * @param level          The level of familiarity with the word
     */
    public FlashCard(OriginalWord originalWord, TranslatedWord translatedWord, Date whenToReview, int level) {
        this.level = new ProficiencyLevel(level);
        this.whenToReview = whenToReview;
        this.translatedWord = translatedWord;
        this.originalWord = originalWord;
    }
    public OriginalWord getOriginalWord() {
        return originalWord;
    }

    public TranslatedWord getTranslatedWord() {
        return translatedWord;
    }

    public Date getWhenToReview() {
        return whenToReview;
    }

    public ProficiencyLevel getLevel() {
        return level;
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
    public boolean hasKeyword(String inputWord) {
        return this.originalWord.hasSubpart(inputWord) || this.translatedWord.hasSubpart(inputWord);
    }
    /**
     * Formats Flashcard for writing to textFile
     *
     * @return Tab separated String formatted for writing
     */
    @Override
    public String toString() {
        String sb = originalWord + " | " + translatedWord + " | " + whenToReview.toString() + " | " + level + "\n";
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
