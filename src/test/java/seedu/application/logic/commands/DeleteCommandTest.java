package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.logic.commands.CommandTestUtil.showJobAtIndex;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.application.testutil.TypicalIndexes.INDEX_SECOND_JOB;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.Messages;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.job.Job;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private final Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Job jobToDelete = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_JOB);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_JOB_SUCCESS,
                Messages.format(jobToDelete));

        ModelManager expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.deleteJob(jobToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showJobAtIndex(model, INDEX_FIRST_JOB);

        Job jobToDelete = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_JOB);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_JOB_SUCCESS,
                Messages.format(jobToDelete));

        Model expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.deleteJob(jobToDelete);
        showNoJob(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showJobAtIndex(model, INDEX_FIRST_JOB);

        Index outOfBoundIndex = INDEX_SECOND_JOB;
        // ensures that outOfBoundIndex is still in bounds of application book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getApplicationBook().getJobList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_JOB);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_JOB);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_JOB);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(deleteFirstCommand, 5.0f);

        // null -> returns false
        assertNotEquals(deleteFirstCommand, null);

        // different person -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no job.
     */
    private void showNoJob(Model model) {
        model.updateFilteredJobList(p -> false);

        assertTrue(model.getFilteredJobList().isEmpty());
    }
}
