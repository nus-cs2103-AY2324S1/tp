package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.address.logic.commands.CopyCommandTestUtil.checkClipboardContents;
import static seedu.address.logic.commands.CopyCommandTestUtil.getClipboardContents;
import static seedu.address.logic.commands.CopyCommandTestUtil.replaceClipboardContents;
import static seedu.address.testutil.TypicalApplicants.getTypicalAddressBookWithApplicants;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.awt.datatransfer.Transferable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Applicant;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CopyApplicantCommand}.
 */
public class CopyApplicantCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBookWithApplicants(), new UserPrefs());

    @Test
    @DisabledIf("java.awt.GraphicsEnvironment.isHeadless()")
    public void execute_validIndexUnfilteredList_success() {
        // Save clipboard contents before executing command
        Transferable clipboardContentsBefore = getClipboardContents();

        Applicant applicantToCopy = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        CopyApplicantCommand copyApplicantCommand = new CopyApplicantCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(CopyApplicantCommand.MESSAGE_COPY_APPLICANT_SUCCESS,
                applicantToCopy.detailsToCopy());

        // Expected model is the same as the model before executing the command
        assertCommandSuccess(copyApplicantCommand, model, expectedMessage, model);

        // Check clipboard contents is correct
        checkClipboardContents(applicantToCopy.detailsToCopy());

        // Restore clipboard contents
        replaceClipboardContents(clipboardContentsBefore);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        CopyApplicantCommand copyApplicantCommand = new CopyApplicantCommand(outOfBoundIndex);

        assertCommandFailure(copyApplicantCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    @DisabledIf("java.awt.GraphicsEnvironment.isHeadless()")
    public void execute_validIndexFilteredList_success() {
        // Save clipboard contents before executing command
        Transferable clipboardContentsBefore = getClipboardContents();

        showApplicantAtIndex(model, INDEX_FIRST_PERSON);

        Applicant applicantToCopy = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        CopyApplicantCommand copyApplicantCommand = new CopyApplicantCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(CopyApplicantCommand.MESSAGE_COPY_APPLICANT_SUCCESS,
                applicantToCopy.detailsToCopy());

        // Expected model is the same as the model before executing the command
        assertCommandSuccess(copyApplicantCommand, model, expectedMessage, model);

        // Check clipboard contents is correct
        checkClipboardContents(applicantToCopy.detailsToCopy());

        // Restore clipboard contents
        replaceClipboardContents(clipboardContentsBefore);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getApplicantList().size());

        CopyApplicantCommand copyApplicantCommand = new CopyApplicantCommand(outOfBoundIndex);

        assertCommandFailure(copyApplicantCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CopyApplicantCommand copyApplicantFirstCommand = new CopyApplicantCommand(INDEX_FIRST_PERSON);
        CopyApplicantCommand copyApplicantSecondCommand = new CopyApplicantCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(copyApplicantFirstCommand, copyApplicantFirstCommand);

        // same values -> returns true
        CopyApplicantCommand copyApplicantFirstCommandCopy = new CopyApplicantCommand(INDEX_FIRST_PERSON);
        assertEquals(copyApplicantFirstCommand, copyApplicantFirstCommandCopy);

        // different types -> returns false
        assertFalse(copyApplicantFirstCommand.equals(1));

        // null -> returns false
        assertNotEquals(null, copyApplicantFirstCommand);

        // different person -> returns false
        assertNotEquals(copyApplicantFirstCommand, copyApplicantSecondCommand);
    }

    @Test
    public void toStringMethod() {
        Index applicantIndex = Index.fromOneBased(1);
        CopyApplicantCommand copyApplicantCommand = new CopyApplicantCommand(applicantIndex);
        String expected = CopyApplicantCommand.class.getCanonicalName() + "{applicantIndex=" + applicantIndex + "}";
        assertEquals(expected, copyApplicantCommand.toString());
    }
}
