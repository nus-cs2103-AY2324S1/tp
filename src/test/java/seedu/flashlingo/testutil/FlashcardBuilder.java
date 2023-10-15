package seedu.flashlingo.testutil;

import java.util.Date;

import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

/**
 * A utility class to help with building Person objects.
 */
public class FlashcardBuilder {

    public static final String DEFAULT_ORIGINAL_WORD = "Hello";
    public static final String DEFAULT_ORIGINAL_WORD_LANGUAGE = "English";
    public static final String DEFAULT_TRANSLATION = "你好";
    public static final String DEFAULT_TRANSLATION_LANGUAGE = "Mandarin";
    private OriginalWord originalWord;
    private TranslatedWord translation;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        originalWord = new OriginalWord(DEFAULT_ORIGINAL_WORD, DEFAULT_ORIGINAL_WORD_LANGUAGE);
        translation = new TranslatedWord(DEFAULT_TRANSLATION, DEFAULT_TRANSLATION_LANGUAGE);
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
    public FlashcardBuilder withOriginalWord(String word, String language) {
        this.originalWord = new OriginalWord(word, language);
        return this;
    }

    /**
     * Sets the {@code translation} of the {@code flashcard} that we are building.
     */
    public FlashcardBuilder withTranslation(String translation, String language) {
        this.translation = new TranslatedWord(translation, language);
        return this;
    }

    public FlashCard build() {
        return new FlashCard(originalWord, translation, new Date(), 1);
    }

}
