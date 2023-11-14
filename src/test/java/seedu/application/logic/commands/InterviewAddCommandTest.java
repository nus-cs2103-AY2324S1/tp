package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.application.testutil.TypicalInterviews.CHEF_INTERVIEW;
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
import seedu.application.testutil.InterviewBuilder;

public class InterviewAddCommandTest {

    private final Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void constructor_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewAddCommand(null, null));
    }

    @Test
    public void execute_validInput_success() throws CommandException {
        Interview interviewToAdd = new InterviewBuilder(CHEF_INTERVIEW).build();
        InterviewAddCommand command = new InterviewAddCommand(INDEX_FIRST, interviewToAdd);
        String expectedMessage = String.format(InterviewAddCommand.MESSAGE_SUCCESS,
                Messages.format(interviewToAdd));
        assertEquals(expectedMessage, command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_duplicateInterview_throwsCommandException() {
        Job job = model.getFilteredJobList().get(0);
        Interview interview = job.getInterview(Index.fromOneBased(1));
        Interview existingInterview = new Interview(interview.getInterviewType(), interview.getInterviewDateTime(),
                interview.getInterviewAddress());
        InterviewAddCommand command = new InterviewAddCommand(INDEX_FIRST, existingInterview);
        assertCommandFailure(command, model, InterviewAddCommand.MESSAGE_DUPLICATE_INTERVIEW);
    }

    @Test
    public void equals() {
        Index targetIndex = Index.fromOneBased(1);
        Interview chefInterview = new InterviewBuilder().withType("Behavioural").build();
        Interview cleanerInterview = new InterviewBuilder().withType("Online").build();

        InterviewAddCommand addChefInterviewCommand = new InterviewAddCommand(targetIndex, chefInterview);
        InterviewAddCommand addCleanerInterviewCommand = new InterviewAddCommand(targetIndex, cleanerInterview);

        // same object -> returns true
        assertEquals(addChefInterviewCommand, addChefInterviewCommand);

        // same values -> returns true
        InterviewAddCommand addChefInterviewCommandCopy = new InterviewAddCommand(targetIndex, chefInterview);
        assertEquals(addChefInterviewCommand, addChefInterviewCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addChefInterviewCommand);

        // null -> returns false
        assertNotEquals(null, addChefInterviewCommand);

        // different job -> returns false
        assertNotEquals(addChefInterviewCommand, addCleanerInterviewCommand);

        // null -> returns false
        assertNotEquals(null, addCleanerInterviewCommand);
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        Interview interview = new InterviewBuilder().build();
        InterviewAddCommand interviewAddCommand = new InterviewAddCommand(targetIndex, interview);
        String expected = InterviewAddCommand.class.getCanonicalName() + "{toAdd=" + interview + "}";
        assertEquals(expected, interviewAddCommand.toString());
    }

}
