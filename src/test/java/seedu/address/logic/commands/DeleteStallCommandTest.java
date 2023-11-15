package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStallAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STALL;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.stall.Stall;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStallCommand}.
 */
public class DeleteStallCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Stall stallToDelete = model.getFilteredStallList().get(INDEX_FIRST_STALL.getZeroBased());
        DeleteStallCommand deleteStallCommand = new DeleteStallCommand(INDEX_FIRST_STALL);

        String expectedMessage = String.format(DeleteStallCommand.MESSAGE_DELETE_STALL_SUCCESS,
                Messages.format(stallToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStall(stallToDelete);

        assertCommandSuccess(deleteStallCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStallList().size() + 1);
        DeleteStallCommand deleteStallCommand = new DeleteStallCommand(outOfBoundIndex);

        assertCommandFailure(deleteStallCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStallAtIndex(model, INDEX_FIRST_STALL);

        Stall stallToDelete = model.getFilteredStallList().get(INDEX_FIRST_STALL.getZeroBased());
        DeleteStallCommand deleteStallCommand = new DeleteStallCommand(INDEX_FIRST_STALL);

        String expectedMessage = String.format(DeleteStallCommand.MESSAGE_DELETE_STALL_SUCCESS,
                Messages.format(stallToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStall(stallToDelete);
        showNoStall(expectedModel);

        assertCommandSuccess(deleteStallCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStallAtIndex(model, INDEX_FIRST_STALL);

        Index outOfBoundIndex = INDEX_SECOND_STALL;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStallList().size());

        DeleteStallCommand deleteStallCommand = new DeleteStallCommand(outOfBoundIndex);

        assertCommandFailure(deleteStallCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteStallCommand deleteFirstCommand = new DeleteStallCommand(INDEX_FIRST_STALL);
        DeleteStallCommand deleteSecondCommand = new DeleteStallCommand(INDEX_SECOND_STALL);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteStallCommand deleteFirstCommandCopy = new DeleteStallCommand(INDEX_FIRST_STALL);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different stall -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteStallCommand deleteStallCommand = new DeleteStallCommand(targetIndex);
        String expected = DeleteStallCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteStallCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStall(Model model) {
        model.updateFilteredStallList(p -> false);

        assertTrue(model.getFilteredStallList().isEmpty());
    }
}
