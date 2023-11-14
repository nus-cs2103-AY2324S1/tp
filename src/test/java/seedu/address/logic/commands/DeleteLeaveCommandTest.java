package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showLeaveByPerson;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LEAVE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_LEAVE;
import static seedu.address.testutil.TypicalLeaves.getTypicalLeavesBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.leave.Leave;
import seedu.address.testutil.TypicalPersons;

public class DeleteLeaveCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalLeavesBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Leave leaveToDelete = model.getFilteredLeaveList().get(INDEX_FIRST_LEAVE.getZeroBased());
        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(INDEX_FIRST_LEAVE);

        String expectedMessage = String.format(DeleteLeaveCommand.MESSAGE_DELETE_LEAVE_SUCCESS,
                Messages.format(leaveToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getLeavesBook(), new UserPrefs());
        expectedModel.deleteLeave(leaveToDelete);

        assertCommandSuccess(deleteLeaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredLeaveList().size() + 1);
        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(outOfBoundsIndex);

        assertCommandFailure(deleteLeaveCommand, model, Messages.MESSAGE_INVALID_LEAVE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showLeaveByPerson(model, TypicalPersons.BENSON);

        Leave leaveToDelete = model.getFilteredLeaveList().get(INDEX_FIRST_LEAVE.getZeroBased());
        DeleteLeaveCommand command = new DeleteLeaveCommand(INDEX_FIRST_LEAVE);

        String expectedMessage = String.format(DeleteLeaveCommand.MESSAGE_DELETE_LEAVE_SUCCESS,
                Messages.format(leaveToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getLeavesBook(), new UserPrefs());
        expectedModel.deleteLeave(leaveToDelete);
        showNoLeave(expectedModel);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showLeaveByPerson(model, TypicalPersons.BENSON);

        Index outofBoundsIndex = INDEX_SECOND_LEAVE;
        assertTrue(outofBoundsIndex.getZeroBased() < model.getLeavesBook().getLeaveList().size());

        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(outofBoundsIndex);

        assertCommandFailure(deleteLeaveCommand, model, Messages.MESSAGE_INVALID_LEAVE_DISPLAYED_INDEX);

    }

    @Test
    public void equals() {
        DeleteLeaveCommand deleteLeaveFirstCommand = new DeleteLeaveCommand(INDEX_FIRST_LEAVE);
        DeleteLeaveCommand deleteLeaveSecondCommand = new DeleteLeaveCommand(INDEX_SECOND_LEAVE);

        assertTrue(deleteLeaveFirstCommand.equals(deleteLeaveFirstCommand));

        DeleteLeaveCommand deleteLeaveFirstCommandCopy = new DeleteLeaveCommand(INDEX_FIRST_LEAVE);
        assertTrue(deleteLeaveFirstCommand.equals(deleteLeaveFirstCommandCopy));

        assertFalse(deleteLeaveFirstCommand.equals(1));

        assertFalse(deleteLeaveFirstCommand.equals(null));

        assertFalse(deleteLeaveFirstCommand.equals(deleteLeaveSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one
     */
    private void showNoLeave(Model model) {
        model.updateFilteredLeaveList(l -> false);

        assertTrue(model.getFilteredLeaveList().isEmpty());
    }
}
