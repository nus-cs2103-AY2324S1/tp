package seedu.address.testutil;

import java.util.Date;

import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.OriginalWord;
import seedu.flashlingo.model.flashcard.Translation;

/**
 * A utility class to help with building Person objects.
 */
public class FlashcardBuilder {

    public static final String DEFAULT_ORIGINAL_WORD = "Hello";
    public static final String DEFAULT_TRANSLATION = "你好";
    private OriginalWord originalWord;
    private Translation translation;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        originalWord = new OriginalWord(DEFAULT_ORIGINAL_WORD);
        translation = new Translation(DEFAULT_TRANSLATION);
    }

    /**
     * Initializes the FlashcardBuilder with the data of {@code personToCopy}.
     */
    public FlashcardBuilder(FlashCard flashCardToCopy) {
        originalWord = flashCardToCopy.getOriginalWord();
        translation = flashCardToCopy.getTranslatedWord();
    }

    /**
     * Sets the {@code originalWord} of the {@code flashcard} that we are building.
     */
    public FlashcardBuilder withOriginalWord(String word) {
        this.originalWord = new OriginalWord(word);
        return this;
    }

    /**
     * Sets the {@code translation} of the {@code flashcard} that we are building.
     */
    public FlashcardBuilder withTranslation(String translation) {
        this.translation = new Translation(translation);
        return this;
    }

    public FlashCard build() {
        return new FlashCard(originalWord, translation, new Date(), 1);
    }

}
