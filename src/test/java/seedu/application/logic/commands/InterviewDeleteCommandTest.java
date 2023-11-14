package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.application.testutil.TypicalIndexes.*;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.job.Job;
import seedu.application.model.job.interview.Interview;

class InterviewDeleteCommandTest {

    private final Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void execute_validInput_success() throws CommandException {
        InterviewDeleteCommand command = new InterviewDeleteCommand(INDEX_FIRST, INDEX_FIRST);
        Job jobToDeleteFrom = model.getFilteredJobList().get(INDEX_FIRST.getZeroBased());
        Interview interviewToBeDeleted = jobToDeleteFrom.getInterview(INDEX_FIRST);
        String expectedMessage = String.format(InterviewDeleteCommand.MESSAGE_SUCCESS,
                Messages.format(interviewToBeDeleted));
        assertEquals(expectedMessage, command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_validJobIndexWithInvalidInterviewIndex_throwsCommandException() {
        Index jobIndex = Index.fromOneBased(model.getFilteredJobList().size());
        Job jobToDeleteInterview = model.getFilteredJobList().get(jobIndex.getZeroBased());
        Index outOfBoundsInterviewindex = Index.fromOneBased(jobToDeleteInterview.interviewLength() + 1);

        InterviewDeleteCommand interviewDeleteCommand = new InterviewDeleteCommand(jobIndex, outOfBoundsInterviewindex);

        assertCommandFailure(interviewDeleteCommand, model, Messages.MESSAGE_INVALID_INTERVIEW);
    }

    @Test
    public void execute_invalidJobIndexWithValidInterviewIndex_throwsCommandException() {
        Index outOfBoundsjobIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        InterviewDeleteCommand interviewDeleteCommand = new InterviewDeleteCommand(outOfBoundsjobIndex, INDEX_FIRST);

        assertCommandFailure(interviewDeleteCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        InterviewDeleteCommand deleteInterviewFirstCommand = new InterviewDeleteCommand(INDEX_FIRST, INDEX_FIRST);
        InterviewDeleteCommand deleteInterviewSecondCommand = new InterviewDeleteCommand(INDEX_SECOND, INDEX_FIRST);
        InterviewDeleteCommand deleteInterviewThirdCommand = new InterviewDeleteCommand(INDEX_FIRST, INDEX_THIRD);

        // same object -> returns true
        assertEquals(deleteInterviewFirstCommand, deleteInterviewFirstCommand);

        // same values -> returns true
        InterviewDeleteCommand deleteFirstCommandCopy = new InterviewDeleteCommand(INDEX_FIRST, INDEX_FIRST);
        assertEquals(deleteInterviewFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(deleteInterviewFirstCommand, 5.0f);

        // null -> returns false
        assertNotEquals(deleteInterviewFirstCommand, null);

        // different job index -> returns false
        assertNotEquals(deleteInterviewFirstCommand, deleteInterviewSecondCommand);

        // different interview index -> returns false
        assertNotEquals(deleteInterviewFirstCommand, deleteInterviewThirdCommand);
    }

    @Test
    public void toStringMethod() {
        Index jobIndex = Index.fromOneBased(1);
        Index interviewIndex = Index.fromOneBased(1);
        InterviewDeleteCommand interviewDeleteCommand = new InterviewDeleteCommand(jobIndex, interviewIndex);
        String expected = InterviewDeleteCommand.class.getCanonicalName() + "{jobIndex=" + jobIndex + ", "
                + "interviewIndex=" + interviewIndex + "}";
        assertEquals(expected, interviewDeleteCommand.toString());
    }
}
