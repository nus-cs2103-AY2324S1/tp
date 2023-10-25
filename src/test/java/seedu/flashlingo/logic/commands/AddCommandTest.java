package seedu.flashlingo.logic.commands;

import static seedu.flashlingo.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

public class AddCommandTest {

    @Test
    public void constructor_nullFlashCard_throwsNullPointerException() {
        // Check that the constructor throws a NullPointerException when a null FlashCard is provided.
        OriginalWord original = new OriginalWord("hello", "English");
        TranslatedWord translation = new TranslatedWord("你好", "Mandarin");
        assertThrows(NullPointerException.class, () -> new AddCommand(null, translation));
        assertThrows(NullPointerException.class, () -> new AddCommand(original, null));
        assertThrows(NullPointerException.class, () -> new AddCommand(null, null));
    }
}
