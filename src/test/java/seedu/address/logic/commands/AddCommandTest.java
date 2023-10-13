package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.logic.commands.AddCommand;
import seedu.flashlingo.logic.commands.CommandResult;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.OriginalWord;
import seedu.flashlingo.model.flashcard.Translation;

public class AddCommandTest {

    @Test
    public void constructor_nullFlashCard_throwsNullPointerException() {
        // Check that the constructor throws a NullPointerException when a null FlashCard is provided.
        OriginalWord original = new OriginalWord("hello");
        Translation translation = new Translation("你好");
        assertThrows(NullPointerException.class, () -> new AddCommand(null, translation));
        assertThrows(NullPointerException.class, () -> new AddCommand(original, null));
        assertThrows(NullPointerException.class, () -> new AddCommand(null, null));
    }
}