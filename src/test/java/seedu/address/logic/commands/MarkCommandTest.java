package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInterviewAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;

public class MarkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_interviewNotYetDone_success() {
        Interview interviewToMark = model.getFilteredInterviewList().get(INDEX_FIRST.getZeroBased());

        // Ensures interview to be marked is not yet done
        assertFalse(interviewToMark.isDone());

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST);

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_INTERVIEW_SUCCESS,
                Messages.formatInterview(interviewToMark));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Interview doneInterview = new Interview(
                interviewToMark.getInterviewApplicant(),
                interviewToMark.getJobRole(),
                interviewToMark.getRating(),
                interviewToMark.getInterviewStartTime(),
                interviewToMark.getInterviewEndTime(),
                 true);
        expectedModel.setInterview(interviewToMark, doneInterview);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_interviewAlreadyDone_failure() {
        Interview interviewToMark = model.getFilteredInterviewList().get(INDEX_SECOND.getZeroBased());

        // Ensures interview to be marked is already done
        assertTrue(interviewToMark.isDone());

        MarkCommand markCommand = new MarkCommand(INDEX_SECOND);

        String expectedMessage = String.format(MarkCommand.MESSAGE_ALREADY_DONE,
                Messages.formatInterview(interviewToMark));

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);

        MarkCommand markCommand = new MarkCommand(outOfBoundIndex);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    /**
     * Filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInterviewAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getInterviewList().size());

        MarkCommand markCommand = new MarkCommand(outOfBoundIndex);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final MarkCommand standardCommand = new MarkCommand(INDEX_FIRST);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new MarkCommand(INDEX_SECOND)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        MarkCommand markCommand = new MarkCommand(index);
        String expected = MarkCommand.class.getCanonicalName() + "{index=" + index + "}";
        assertEquals(expected, markCommand.toString());
    }

}
