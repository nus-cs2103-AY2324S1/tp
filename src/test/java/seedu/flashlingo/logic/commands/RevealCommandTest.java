package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static seedu.flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingoWithOneFlashCard;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

/**
 * Indicates user has not yet memorized the word.
 */
public class RevealCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_reveal_successMessage() {
        Model expectedModel = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
        FlashCard currentCard = model.getFilteredFlashCardList().get(0);
        assertCommandSuccess(new RevealCommand(), model, RevealCommand.MESSAGE_SUCCESS
            + currentCard.getTranslatedWord(), expectedModel);
    }

    @Test
    public void execute_reveal_success() {
        Model model = new ModelManager(getTypicalFlashlingoWithOneFlashCard(), new UserPrefs());
        TranslatedWord expected = model.getFilteredFlashCardList().get(0).getTranslatedWord();
        TranslatedWord actual = model.reveal();
        assertEquals(expected, actual);
    }
    @Test
    public void equals() {
        RevealCommand revealFirstCommand = new RevealCommand();
        assertTrue(revealFirstCommand.equals(revealFirstCommand));
        RevealCommand revealFirstCommandCopy = new RevealCommand();
        assertTrue(revealFirstCommand.equals(revealFirstCommandCopy));
        assertFalse(revealFirstCommand.equals(null));

    }
}
