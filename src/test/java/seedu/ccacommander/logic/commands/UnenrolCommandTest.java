package seedu.ccacommander.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ccacommander.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ccacommander.testutil.TypicalCcaCommander.getTypicalCcaCommander;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_FIRST_ENROLMENT;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_SECOND_MEMBER;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.commons.core.index.Index;
import seedu.ccacommander.logic.Messages;
import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.ModelManager;
import seedu.ccacommander.model.UserPrefs;
import seedu.ccacommander.model.enrolment.Enrolment;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnenrolCommand}.
 */
public class UnenrolCommandTest {

    private Model model = new ModelManager(getTypicalCcaCommander(), new UserPrefs());

    @Test
    public void execute_validEnrolmentUnfilteredList_success() {
        Enrolment enrolmentToDelete = model.getFilteredEnrolmentList().get(INDEX_FIRST_ENROLMENT.getZeroBased());
        UnenrolCommand unenrolCommand = new UnenrolCommand(INDEX_FIRST_MEMBER, INDEX_FIRST_EVENT);

        String commitMessage = String.format(UnenrolCommand.MESSAGE_COMMIT,
                enrolmentToDelete.getMemberAndEventEnrolment());
        String expectedMessage = String.format(UnenrolCommand.MESSAGE_DELETE_ENROLMENT_SUCCESS,
                Messages.format(enrolmentToDelete));

        ModelManager expectedModel = new ModelManager(model.getCcaCommander(), new UserPrefs());
        expectedModel.deleteEnrolment(enrolmentToDelete);
        expectedModel.commit(commitMessage);

        assertCommandSuccess(unenrolCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unenrolmentForNonExistentEnrolmentUnfilteredList_throwsCommandException() {
        UnenrolCommand unenrolCommand = new UnenrolCommand(INDEX_FIRST_MEMBER, INDEX_SECOND_EVENT);
        String expectedFailedMessage = Messages.MESSAGE_ENROLMENT_NOT_FOUND;
        assertCommandFailure(unenrolCommand, model, expectedFailedMessage);
    }
    @Test
    public void execute_duplicateUnenrolmentUnfilteredList_throwsCommandException() {
        Enrolment enrolmentToDelete = model.getFilteredEnrolmentList().get(INDEX_FIRST_ENROLMENT.getZeroBased());
        UnenrolCommand unenrolCommand = new UnenrolCommand(INDEX_FIRST_MEMBER, INDEX_FIRST_EVENT);

        String commitMessage = String.format(UnenrolCommand.MESSAGE_COMMIT,
                enrolmentToDelete.getMemberAndEventEnrolment());
        String expectedSuccessMessage = String.format(UnenrolCommand.MESSAGE_DELETE_ENROLMENT_SUCCESS,
                Messages.format(enrolmentToDelete));
        String expectedFailedMessage = Messages.MESSAGE_ENROLMENT_NOT_FOUND;

        ModelManager expectedModel = new ModelManager(model.getCcaCommander(), new UserPrefs());
        expectedModel.deleteEnrolment(enrolmentToDelete);
        expectedModel.commit(commitMessage);

        assertCommandSuccess(unenrolCommand, model, expectedSuccessMessage, expectedModel);
        assertCommandFailure(unenrolCommand, model, expectedFailedMessage);
    }

    @Test
    public void execute_invalidMemberIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundMemberIndex = Index.fromZeroBased(model.getFilteredMemberList().size() + 1);
        Index eventIndex = Index.fromZeroBased(1);
        UnenrolCommand unenrolCommand = new UnenrolCommand(outOfBoundMemberIndex, eventIndex);

        assertCommandFailure(unenrolCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidEventIndexUnfilteredList_throwsCommandException() {
        Index memberIndex = Index.fromZeroBased(1);
        Index outOfBoundEventIndex = Index.fromZeroBased(model.getFilteredEventList().size() + 1);
        UnenrolCommand unenrolCommand = new UnenrolCommand(memberIndex, outOfBoundEventIndex);

        assertCommandFailure(unenrolCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidMemberEventIndexesUnfilteredList_throwsCommandException() {
        Index outOfBoundMemberIndex = Index.fromZeroBased(model.getFilteredMemberList().size() + 1);
        Index outOfBoundEventIndex = Index.fromZeroBased(model.getFilteredEventList().size() + 1);
        UnenrolCommand unenrolCommand = new UnenrolCommand(outOfBoundMemberIndex, outOfBoundEventIndex);

        assertCommandFailure(unenrolCommand, model, Messages.MESSAGE_INVALID_MEMBER_AND_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnenrolCommand unenrolFirstCommand = new UnenrolCommand(INDEX_FIRST_MEMBER, INDEX_FIRST_EVENT);
        UnenrolCommand unenrolSecondCommand = new UnenrolCommand(INDEX_SECOND_MEMBER, INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(unenrolFirstCommand.equals(unenrolFirstCommand));

        // same values -> returns true
        UnenrolCommand unenrolFirstCommandCopy = new UnenrolCommand(INDEX_FIRST_MEMBER, INDEX_FIRST_EVENT);
        assertTrue(unenrolFirstCommand.equals(unenrolFirstCommandCopy));

        // different types -> returns false
        assertFalse(unenrolFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unenrolFirstCommand.equals(null));

        // different member -> returns false
        assertFalse(unenrolFirstCommand.equals(unenrolSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index memberIndex = Index.fromOneBased(1);
        Index eventIndex = Index.fromOneBased(1);
        UnenrolCommand unenrolCommand = new UnenrolCommand(memberIndex, eventIndex);
        String expected = UnenrolCommand.class.getCanonicalName() + "{memberIndex=" + memberIndex
                + ", eventIndex=" + eventIndex + "}";
        assertEquals(expected, unenrolCommand.toString());
    }

    @Test
    /**
     * Updates {@code model}'s filtered enrolment list to show no one.
     */
    private void showNoEnrolment(Model model) {
        model.updateFilteredEnrolmentList(p -> false);

        assertTrue(model.getFilteredEnrolmentList().isEmpty());
    }
}
