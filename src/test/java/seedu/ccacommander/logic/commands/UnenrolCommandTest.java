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

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.commons.core.index.Index;
import seedu.ccacommander.logic.Messages;
import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.ModelManager;
import seedu.ccacommander.model.UserPrefs;
import seedu.ccacommander.model.enrolment.Enrolment;
import seedu.ccacommander.model.enrolment.exceptions.EnrolmentNotFoundException;
import seedu.ccacommander.testutil.TypicalEnrolments;
import seedu.ccacommander.testutil.TypicalEvents;
import seedu.ccacommander.testutil.TypicalMembers;

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
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEnrolmentList().size() + 1);
        UnenrolCommand unenrolCommand = new UnenrolCommand(outOfBoundIndex, outOfBoundIndex);

        assertCommandFailure(unenrolCommand, model, Messages.MESSAGE_INVALID_MEMBER_AND_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_findEnrolmentFromList_success() {
        List<Enrolment> enrolmentList = new ArrayList<>();

        Enrolment enrolmentToFindOne = TypicalEnrolments.ALICE_AURORA;
        Enrolment enrolmentToFindTwo = TypicalEnrolments.GEORGE_GRAVITY_DISCOVERY_DAY;

        enrolmentList.add(enrolmentToFindOne);
        enrolmentList.add(enrolmentToFindTwo);

        assertEquals(enrolmentToFindOne, UnenrolCommand.findEnrolmentFromList(enrolmentList,
                TypicalMembers.ALICE.getName(), TypicalEvents.AURORA_BOREALIS.getName()));
        assertEquals(enrolmentToFindTwo, UnenrolCommand.findEnrolmentFromList(enrolmentList,
                TypicalMembers.GEORGE.getName(), TypicalEvents.GRAVITY_DISCOVERY_DAY.getName()));
    }

    @Test
    public void execute_findEnrolmentFromList_fail() {
        List<Enrolment> enrolmentList = new ArrayList<>();

        Enrolment enrolmentToFindOne = TypicalEnrolments.ALICE_AURORA;
        Enrolment enrolmentToFindTwo = TypicalEnrolments.GEORGE_GRAVITY_DISCOVERY_DAY;

        enrolmentList.add(enrolmentToFindOne);
        enrolmentList.add(enrolmentToFindTwo);

        try {
            UnenrolCommand.findEnrolmentFromList(enrolmentList,
                    TypicalMembers.CARL.getName(), TypicalEvents.BOXING_DAY.getName());
        } catch (EnrolmentNotFoundException ee) {
            assertEquals("Enrolment cannot be found", ee.getMessage());
        }
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

    /**
     * Updates {@code model}'s filtered enrolment list to show no one.
     */
    private void showNoEnrolment(Model model) {
        model.updateFilteredEnrolmentList(p -> false);

        assertTrue(model.getFilteredEnrolmentList().isEmpty());
    }
}
