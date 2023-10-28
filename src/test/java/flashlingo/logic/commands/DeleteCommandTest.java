package flashlingo.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.logic.commands.CommandResult;
import seedu.flashlingo.logic.commands.DeleteCommand;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.FlashCard;

import java.util.List;

import static flashlingo.logic.commands.CommandTestUtil.*;
import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static flashlingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static flashlingo.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Deletes a flashcard identified using it's displayed index from Flashlingo.
 */
public class DeleteCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        FlashCard flashCardToDelete = model.getFilteredFlashCardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_FLASHCARD);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_FLASHCARD_SUCCESS,
                Messages.format(flashCardToDelete));

        ModelManager expectedModel = new ModelManager(model.getFlashlingo(), new UserPrefs());
        expectedModel.deleteFlashCard(flashCardToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashCardList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFlashCardAtIndex(model, INDEX_FIRST_FLASHCARD);

        FlashCard flashCardToDelete = model.getFilteredFlashCardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_FLASHCARD);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_FLASHCARD_SUCCESS,
                Messages.format(flashCardToDelete));

        Model expectedModel = new ModelManager(model.getFlashlingo(), new UserPrefs());
        expectedModel.deleteFlashCard(flashCardToDelete);
        showNoFlashCard(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFlashCardAtIndex(model, INDEX_FIRST_FLASHCARD);

        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashlingo().getFlashCardList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_FLASHCARD);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_FLASHCARD);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_FLASHCARD);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different FlashCard -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoFlashCard(Model model) {
        model.updateFilteredFlashCardList(p -> false);

        assertTrue(model.getFilteredFlashCardList().isEmpty());
    }

//    public static final String COMMAND_WORD = "delete";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD
//            + ": Deletes the word identified by the index number used in the displayed flashcard list.\n"
//            + "Parameters: INDEX (must be a positive integer)\n"
//            + "Example: " + COMMAND_WORD + " 1";
//
//    public static final String MESSAGE_DELETE_FLASHCARD_SUCCESS = "Deleted Word: %1$s";
//
//    private final Index targetIndex;
//
//    public DeleteCommandTest(Index targetIndex) {
//        this.targetIndex = targetIndex;
//    }
//
//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//        List<FlashCard> lastShownList = model.getFilteredFlashCardList();
//
//        if (targetIndex.getZeroBased() >= lastShownList.size()) {
//            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
//        }
//
//        FlashCard flashCardToDelete = lastShownList.get(targetIndex.getZeroBased());
//        System.out.println(flashCardToDelete);
//        model.deleteFlashCard(flashCardToDelete);
//        return new CommandResult(String.format(MESSAGE_DELETE_FLASHCARD_SUCCESS, Messages.format(flashCardToDelete)));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof DeleteCommandTest)) {
//            return false;
//        }
//
//        DeleteCommandTest otherDeleteCommand = (DeleteCommandTest) other;
//        return targetIndex.equals(otherDeleteCommand.targetIndex);
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .add("targetIndex", targetIndex)
//                .toString();
//    }
}
