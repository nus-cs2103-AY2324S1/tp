package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.index.Index;
import seedu.application.model.job.interview.Interview;
import seedu.application.testutil.InterviewBuilder;

public class InterviewAddCommandTest {

    @Test
    public void constructor_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewAddCommand(null, null));
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
