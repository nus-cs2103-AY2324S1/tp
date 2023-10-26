package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMemberAtIndex;
import static seedu.address.logic.commands.CopyCommandTestUtil.checkClipboardContents;
import static seedu.address.logic.commands.CopyCommandTestUtil.getClipboardContents;
import static seedu.address.logic.commands.CopyCommandTestUtil.replaceClipboardContents;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalMembers.getTypicalAddressBookWithMembers;

import java.awt.datatransfer.Transferable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Member;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CopyMemberCommand}.
 */
public class CopyMemberCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBookWithMembers(), new UserPrefs());

    @Test
    @DisabledIf("java.awt.GraphicsEnvironment.isHeadless()")
    public void execute_validIndexUnfilteredList_success() {
        // Save clipboard contents before executing command
        Transferable clipboardContentsBefore = getClipboardContents();

        Member memberToCopy = model.getFilteredMemberList().get(INDEX_FIRST_PERSON.getZeroBased());
        CopyMemberCommand copyMemberCommand = new CopyMemberCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(CopyMemberCommand.MESSAGE_COPY_MEMBER_SUCCESS,
                memberToCopy.detailsToCopy());

        // Expected model is the same as the model before executing the command
        assertCommandSuccess(copyMemberCommand, model, expectedMessage, model);

        // Check clipboard contents is correct
        checkClipboardContents(memberToCopy.detailsToCopy());

        // Restore clipboard contents
        replaceClipboardContents(clipboardContentsBefore);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMemberList().size() + 1);
        CopyMemberCommand copyMemberCommand = new CopyMemberCommand(outOfBoundIndex);

        assertCommandFailure(copyMemberCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    @DisabledIf("java.awt.GraphicsEnvironment.isHeadless()")
    public void execute_validIndexFilteredList_success() {
        // Save clipboard contents before executing command
        Transferable clipboardContentsBefore = getClipboardContents();

        showMemberAtIndex(model, INDEX_FIRST_PERSON);

        Member memberToCopy = model.getFilteredMemberList().get(INDEX_FIRST_PERSON.getZeroBased());
        CopyMemberCommand copyMemberCommand = new CopyMemberCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(CopyMemberCommand.MESSAGE_COPY_MEMBER_SUCCESS,
                memberToCopy.detailsToCopy());

        // Expected model is the same as the model before executing the command
        assertCommandSuccess(copyMemberCommand, model, expectedMessage, model);

        // Check clipboard contents is correct
        checkClipboardContents(memberToCopy.detailsToCopy());

        // Restore clipboard contents
        replaceClipboardContents(clipboardContentsBefore);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMemberAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getMemberList().size());

        CopyMemberCommand copyMemberCommand = new CopyMemberCommand(outOfBoundIndex);

        assertCommandFailure(copyMemberCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CopyMemberCommand copyMemberFirstCommand = new CopyMemberCommand(INDEX_FIRST_PERSON);
        CopyMemberCommand copyMemberSecondCommand = new CopyMemberCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(copyMemberFirstCommand, copyMemberFirstCommand);

        // same values -> returns true
        CopyMemberCommand copyMemberFirstCommandCopy = new CopyMemberCommand(INDEX_FIRST_PERSON);
        assertEquals(copyMemberFirstCommand, copyMemberFirstCommandCopy);

        // different types -> returns false
        assertFalse(copyMemberFirstCommand.equals(1));

        // null -> returns false
        assertNotEquals(null, copyMemberFirstCommand);

        // different person -> returns false
        assertNotEquals(copyMemberFirstCommand, copyMemberSecondCommand);
    }

    @Test
    public void toStringMethod() {
        Index memberIndex = Index.fromOneBased(1);
        CopyMemberCommand copyMemberCommand = new CopyMemberCommand(memberIndex);
        String expected = CopyMemberCommand.class.getCanonicalName() + "{memberIndex=" + memberIndex + "}";
        assertEquals(expected, copyMemberCommand.toString());
    }
}
