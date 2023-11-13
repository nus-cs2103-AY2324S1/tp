package seedu.letsgethired.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.letsgethired.logic.commands.CommandTestUtil.showInternApplicationAtIndex;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternTracker;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.ModelManager;
import seedu.letsgethired.model.UserPrefs;
import seedu.letsgethired.model.application.InternApplication;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalInternTracker(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        InternApplication internApplicationToDelete = model
                .getFilteredInternApplicationList()
                .get(INDEX_FIRST_APPLICATION
                .getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_APPLICATION);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INTERN_APPLICATION_SUCCESS,
                Messages.formatFeedback(internApplicationToDelete));

        ModelManager expectedModel = new ModelManager(model.getInternTracker(), new UserPrefs());
        expectedModel.deleteInternApplication(internApplicationToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternApplicationList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        InternApplication internApplicationToDelete = model
                .getFilteredInternApplicationList().get(INDEX_FIRST_APPLICATION
                .getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_APPLICATION);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_INTERN_APPLICATION_SUCCESS,
                Messages.formatFeedback(internApplicationToDelete));

        Model expectedModel = new ModelManager(model.getInternTracker(), new UserPrefs());
        expectedModel.deleteInternApplication(internApplicationToDelete);
        showNoInternApplication(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of intern tracker list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternTracker().getApplicationList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_APPLICATION);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_APPLICATION);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_APPLICATION);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different intern application -> returns false
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
    private void showNoInternApplication(Model model) {
        model.updateFilteredInternApplicationList(p -> false);

        assertTrue(model.getFilteredInternApplicationList().isEmpty());
    }
}
