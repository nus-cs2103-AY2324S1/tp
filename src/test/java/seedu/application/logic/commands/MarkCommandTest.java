package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.VALID_STATUS_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_STATUS_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.logic.commands.CommandTestUtil.showJobAtIndex;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.application.testutil.TypicalIndexes.INDEX_SECOND_JOB;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.Messages;
import seedu.application.model.ApplicationBook;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.job.Job;
import seedu.application.model.job.Status;
import seedu.application.testutil.JobBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkCommand.
 */
public class MarkCommandTest {
    private Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void execute_markStatusUnfilteredList_success() {
        Job firstJob = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        Job editedJob = new JobBuilder(firstJob).withStatus(Status.JobStatus.PENDING.toString()).build();

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_JOB,
                new Status(editedJob.getStatus().status));

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new ApplicationBook(model.getApplicationBook()), new UserPrefs());
        expectedModel.setJob(firstJob, editedJob);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showJobAtIndex(model, INDEX_FIRST_JOB);

        Job firstJob = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        Job editedJob = new JobBuilder(model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased()))
                .withStatus(Status.IN_PROGRESS).build();

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_JOB,
                new Status(editedJob.getStatus().status));

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new ApplicationBook(model.getApplicationBook()), new UserPrefs());
        expectedModel.setJob(firstJob, editedJob);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidJobIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex, new Status(VALID_STATUS_CLEANER));

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidJobIndexFilteredList_failure() {
        showJobAtIndex(model, INDEX_FIRST_JOB);
        Index outOfBoundIndex = INDEX_SECOND_JOB;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getApplicationBook().getJobList().size());

        MarkCommand markCommand = new MarkCommand(outOfBoundIndex, new Status(VALID_STATUS_CLEANER));

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        System.out.println(VALID_STATUS_CHEF + " " + VALID_STATUS_CLEANER);
        final MarkCommand standardCommand = new MarkCommand(INDEX_FIRST_JOB, new Status(VALID_STATUS_CHEF));

        // same values -> returns true
        MarkCommand commandWithSameValues = new MarkCommand(INDEX_FIRST_JOB, new Status(VALID_STATUS_CHEF));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new MarkCommand(INDEX_SECOND_JOB, new Status(VALID_STATUS_CHEF))));

        // different status -> returns false
        assertFalse(standardCommand.equals(new MarkCommand(INDEX_FIRST_JOB, new Status(VALID_STATUS_CLEANER))));
    }
}
