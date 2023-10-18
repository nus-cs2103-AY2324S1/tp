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

    private boolean isUpdated;
    private int originalLevel; // For undo function
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
        this.isUpdated = false;
        this.originalLevel = level;
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

    public ProficiencyLevel getProficiencyLevel() {
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

    /**
     * Returns true if the original word or the translation contains the keyword.
     * @param inputWord The keyword to check for
     * @return True or False depending on whether the keyword is found
     */
    public boolean hasKeyword(String inputWord) {
        return this.originalWord.hasSubpart(inputWord) || this.translatedWord.hasSubpart(inputWord);
    }

    /**
     * Returns true if the review date is before the current date.
     * @return True or False depending on whether the review date is before the current date
     */
    public boolean isOverdue() {
        return this.whenToReview.before(new Date());
    }

    /**
     * Returns true if the original word or the translation is of the language.
     * @param language The language to check for
     * @return True or False depending on whether the language is found
     */
    public boolean isSameLanguage(String language) {
        return this.originalWord.isSameLanguage(language) || this.translatedWord.isSameLanguage(language);
    }

    /**
     * Formats Flashcard for writing to textFile
     *
     * @return Tab separated String formatted for writing
     */
    @Override
    public String toString() {
        String sb = originalWord + " | " + originalWord.getLanguage() + " | " + translatedWord + " | "
                + originalWord.getLanguage() + " | " + whenToReview.toString() + " | " + level + "\n";
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


    /**
     * Handles when user clicks yes/no
     * @param isSuccess Whether user has successfully remembered the word
     */
    public void handleUserInput(boolean isSuccess) {
        if (isUpdated) {
            return;
        }
        if (isSuccess) {
            getProficiencyLevel().upgradeLevel();
            updateReviewDate(getProficiencyLevel().calculateNextReviewInterval());
        } else {
            getProficiencyLevel().downgradeLevel();
            updateReviewDate(getProficiencyLevel().calculateNextReviewInterval());
        }
        isUpdated = true;
    }

    public void updateReviewDate(long timeInMs) {
        this.whenToReview = new Date(new Date().getTime() + timeInMs);
    }

    public void undo() {
        if (isUpdated) {
            this.getProficiencyLevel().setLevel(originalLevel);
            this.whenToReview = new Date(new Date().getTime() + getProficiencyLevel().calculateNextReviewInterval());
            isUpdated = false;
        }
    }
}
