package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.application.logic.commands.CommandTestUtil.*;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.application.testutil.TypicalIndexes.INDEX_SECOND;
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
import seedu.application.testutil.EditInterviewDescriptorBuilder;
import seedu.application.testutil.InterviewBuilder;

class InterviewEditCommandTest {
    private final Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
    private Interview interview = new InterviewBuilder().build();
    private InterviewEditCommand.EditInterviewDescriptor descriptor =
            new EditInterviewDescriptorBuilder(interview).build();
    @Test
    public void execute_allFieldsSpecified_success() throws CommandException {
        InterviewEditCommand command = new InterviewEditCommand(INDEX_FIRST, INDEX_FIRST,
                new EditInterviewDescriptorBuilder().withAddress("NUS SR 10")
                        .withType("Case").withDateTime("Jun 13 2025 2100").build());
        Interview editedInterview = new InterviewBuilder().withAddress("NUS SR 10")
                .withType("Case").withDateTime("Jun 13 2025 2100").build();
        String expectedMessage = String.format(InterviewEditCommand.MESSAGE_SUCCESS, Messages.format(editedInterview));
        assertEquals(expectedMessage, command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        InterviewEditCommand command = new InterviewEditCommand(INDEX_FIRST, INDEX_FIRST, descriptor);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_invalidJobIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        InterviewEditCommand command = new InterviewEditCommand(outOfBoundIndex, INDEX_FIRST, descriptor);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidInterviewIndex_failure() {
        Job job = model.getFilteredJobList().get(0);
        Index outOfBoundIndex = Index.fromOneBased(job.interviewLength() + 1);
        InterviewEditCommand command = new InterviewEditCommand(INDEX_FIRST, outOfBoundIndex, descriptor);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_INTERVIEW);
    }

    @Test
    public void execute_duplicateInterview_failure() {
        Job job = model.getFilteredJobList().get(0);
        Interview existingInterview = job.getInterview(Index.fromOneBased(1));
        InterviewEditCommand command = new InterviewEditCommand(INDEX_FIRST, INDEX_FIRST,
                new EditInterviewDescriptorBuilder(existingInterview).build());
        assertCommandFailure(command, model, InterviewEditCommand.MESSAGE_DUPLICATE_INTERVIEW);
    }

    @Test
    public void equals() {
        final InterviewEditCommand standardCommand = new InterviewEditCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        // same values -> returns true
        InterviewEditCommand.EditInterviewDescriptor copyDescriptor =
                new InterviewEditCommand.EditInterviewDescriptor(descriptor);
        InterviewEditCommand commandWithSameValues = new InterviewEditCommand(INDEX_FIRST, INDEX_FIRST, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same command object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> )returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different job index -> returns false
        assertNotEquals(standardCommand, new InterviewEditCommand(INDEX_SECOND, INDEX_FIRST, descriptor));

        // different interview index -> returns false
        assertNotEquals(standardCommand, new InterviewEditCommand(INDEX_FIRST, INDEX_SECOND, descriptor));

        // same descriptor object -> returns true
        assertEquals(descriptor, descriptor);

        // null -> returns false
        assertNotEquals(descriptor, null);
    }

    @Test
    public void toStringMethod() {
        Index jobIndex = Index.fromOneBased(1);
        Index interviewIndex = jobIndex;
        InterviewEditCommand.EditInterviewDescriptor editInterviewDescriptor =
                new InterviewEditCommand.EditInterviewDescriptor();
        InterviewEditCommand interviewEditCommand =
                new InterviewEditCommand(jobIndex, interviewIndex, editInterviewDescriptor);
        String expected = InterviewEditCommand.class.getCanonicalName() + "{jobIndex=" + jobIndex
                + ", interviewIndex=" + interviewIndex + ", editInterviewDescriptor=" + editInterviewDescriptor + "}";
        assertEquals(expected, interviewEditCommand.toString());
    }

}
