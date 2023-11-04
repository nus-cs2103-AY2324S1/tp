package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashlingo.logic.commands.CommandTestUtil.showFlashCardAtIndex;
import static seedu.flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static seedu.flashlingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.flashlingo.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RevealCommand}.
 */
public class RevealCommandTest {

    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        FlashCard flashCard = model.getFilteredFlashCardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        RevealCommand revealCommand = new RevealCommand(INDEX_FIRST_FLASHCARD);

        String expectedMessage = String.format(RevealCommand.MESSAGE_SUCCESS,
          Messages.format(flashCard));

        ModelManager expectedModel = new ModelManager(model.getFlashlingo(), new UserPrefs());
        expectedModel.reveal(flashCard);

        assertCommandSuccess(revealCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashCardList().size() + 1);
        RevealCommand revealCommand = new RevealCommand(outOfBoundIndex);

        assertCommandFailure(revealCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFlashCardAtIndex(model, INDEX_FIRST_FLASHCARD);

        FlashCard flashcardToReveal = model.getFilteredFlashCardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        RevealCommand revealCommand = new RevealCommand(INDEX_FIRST_FLASHCARD);

        String expectedMessage = String.format(RevealCommand.MESSAGE_SUCCESS,
          Messages.format(flashcardToReveal));

        Model expectedModel = new ModelManager(model.getFlashlingo(), new UserPrefs());
        expectedModel.reveal(flashcardToReveal);
        showNoFlashCard(expectedModel);

        assertCommandSuccess(revealCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFlashCardAtIndex(model, INDEX_FIRST_FLASHCARD);

        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashlingo().getFlashCardList().size());

        RevealCommand revealCommand = new RevealCommand(outOfBoundIndex);

        assertCommandFailure(revealCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        RevealCommand revealFirstCommand = new RevealCommand(INDEX_FIRST_FLASHCARD);
        RevealCommand revealSecondCommand = new RevealCommand(INDEX_SECOND_FLASHCARD);

        // same object -> returns true
        assertTrue(revealFirstCommand.equals(revealFirstCommand));

        // same values -> returns true
        RevealCommand revealFirstCommandCopy = new RevealCommand(INDEX_FIRST_FLASHCARD);
        assertTrue(revealFirstCommand.equals(revealFirstCommandCopy));

        // different types -> returns false
        assertFalse(revealFirstCommand.equals(1));

        // null -> returns false
        assertFalse(revealFirstCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(revealFirstCommand.equals(revealSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        RevealCommand revealCommand = new RevealCommand(targetIndex);
        String expected = RevealCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, revealCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoFlashCard(Model model) {
        model.updateFilteredFlashCardList(p -> false);

        assertTrue(model.getFilteredFlashCardList().isEmpty());
    }
}
