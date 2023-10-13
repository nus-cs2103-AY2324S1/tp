package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DEADLINE_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DEADLINE_CLEANER;
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
import seedu.application.model.job.Deadline;
import seedu.application.model.job.Job;
import seedu.application.testutil.JobBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeadlineCommand.
 */
public class DeadlineCommandTest {
    private static final String DEADLINE_STUB = "Dec 31 2030 1200";
    private Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void execute_setDeadlineUnfilteredList_success() {
        Job firstJob = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        Job editedJob = new JobBuilder(firstJob).withDeadline(DEADLINE_STUB).build();

        DeadlineCommand deadlineCommand = new DeadlineCommand(INDEX_FIRST_JOB, new Deadline(editedJob.getDeadline().deadline));

        String expectedMessage = String.format(DeadlineCommand.MESSAGE_SET_DEADLINE_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new ApplicationBook(model.getApplicationBook()), new UserPrefs());
        expectedModel.setJob(firstJob, editedJob);

        assertCommandSuccess(deadlineCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showJobAtIndex(model, INDEX_FIRST_JOB);

        Job firstJob = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        Job editedJob = new JobBuilder(model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased()))
                .withDeadline(DEADLINE_STUB).build();

        DeadlineCommand deadlineCommand = new DeadlineCommand(INDEX_FIRST_JOB, new Deadline(editedJob.getDeadline().deadline));

        String expectedMessage = String.format(DeadlineCommand.MESSAGE_SET_DEADLINE_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new ApplicationBook(model.getApplicationBook()), new UserPrefs());
        expectedModel.setJob(firstJob, editedJob);

        assertCommandSuccess(deadlineCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        DeadlineCommand deadlineCommand = new DeadlineCommand(outOfBoundIndex, new Deadline(VALID_DEADLINE_CLEANER));

        assertCommandFailure(deadlineCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showJobAtIndex(model, INDEX_FIRST_JOB);
        Index outOfBoundIndex = INDEX_SECOND_JOB;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getApplicationBook().getJobList().size());

        DeadlineCommand deadlineCommand = new DeadlineCommand(outOfBoundIndex, new Deadline(VALID_DEADLINE_CLEANER));

        assertCommandFailure(deadlineCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeadlineCommand standardCommand = new DeadlineCommand(INDEX_FIRST_JOB, new Deadline(VALID_DEADLINE_CHEF));

        // same values -> returns true
        DeadlineCommand commandWithSameValues = new DeadlineCommand(INDEX_FIRST_JOB, new Deadline(VALID_DEADLINE_CHEF));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeadlineCommand(INDEX_SECOND_JOB, new Deadline(VALID_DEADLINE_CHEF))));

        // different deadline -> returns false
        assertFalse(standardCommand.equals(new DeadlineCommand(INDEX_FIRST_JOB, new Deadline(VALID_DEADLINE_CLEANER))));
    }
}
