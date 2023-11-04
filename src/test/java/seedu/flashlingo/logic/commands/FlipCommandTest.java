package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandFailure;
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

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code FlipCommand}.
 */
public class FlipCommandTest {

    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashCardList().size() + 1);
        FlipCommand flipCommand = new FlipCommand(outOfBoundIndex);

        assertCommandFailure(flipCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFlashCardAtIndex(model, INDEX_FIRST_FLASHCARD);

        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashlingo().getFlashCardList().size());

        FlipCommand flipCommand = new FlipCommand(outOfBoundIndex);

        assertCommandFailure(flipCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        FlipCommand revealFirstCommand = new FlipCommand(INDEX_FIRST_FLASHCARD);
        FlipCommand revealSecondCommand = new FlipCommand(INDEX_SECOND_FLASHCARD);

        // same object -> returns true
        assertTrue(revealFirstCommand.equals(revealFirstCommand));

        // same values -> returns true
        FlipCommand revealFirstCommandCopy = new FlipCommand(INDEX_FIRST_FLASHCARD);
        assertTrue(revealFirstCommand.equals(revealFirstCommandCopy));

        // different types -> returns false
        assertFalse(revealFirstCommand.equals(1));

        // null -> returns false
        assertFalse(revealFirstCommand.equals(null));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        FlipCommand flipCommand = new FlipCommand(targetIndex);
        String expected = FlipCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, flipCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoFlashCard(Model model) {
        model.updateFilteredFlashCardList(p -> false);

        assertTrue(model.getFilteredFlashCardList().isEmpty());
    }
}
