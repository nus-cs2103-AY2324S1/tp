package seedu.address.logic.commands.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_IN_LIST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandAssignmentTestUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkAssignmentCommand}.
 */

public class UnMarkAssignmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Assignment getAtIndex(int i) {
        return model.getFilteredAssignmentList().get(i);
    }

    @Test
    public void execute_unMark_success() throws CommandException {
        Assignment assignmentToUnMark = getAtIndex(INDEX_FIRST_IN_LIST.getZeroBased());
        model.setAssignment(assignmentToUnMark, assignmentToUnMark.mark());
        assertTrue(getAtIndex(INDEX_FIRST_IN_LIST.getZeroBased())
                .getStatus().toString().equals("complete"));
        UnMarkAssignmentCommand cmd = new UnMarkAssignmentCommand(INDEX_FIRST_IN_LIST);
        cmd.execute(model);
        assertTrue(getAtIndex(INDEX_FIRST_IN_LIST.getZeroBased()).getStatus().toString().equals("incomplete"));
    }

    @Test

    public void execute_unMarkIncompletAssignment_throwsCommandException() {
        Assignment assignmentToUnMark = model.getFilteredAssignmentList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        UnMarkAssignmentCommand markCommand = new UnMarkAssignmentCommand(INDEX_FIRST_IN_LIST);
        model.setAssignment(assignmentToUnMark, assignmentToUnMark.unMark());
        assertCommandFailure(markCommand, model,
                String.format(UnMarkAssignmentCommand.MESSAGE_ASSIGNMENT_ALREADY_INCOMPLETE));
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        UnMarkAssignmentCommand unMarkCommand = new UnMarkAssignmentCommand(outOfBoundIndex);

        CommandAssignmentTestUtil.assertCommandFailure(unMarkCommand, model,
                Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }
    @Test
    public void equals() {
        UnMarkAssignmentCommand unMarkFirstCommand = new UnMarkAssignmentCommand(INDEX_FIRST_IN_LIST);
        UnMarkAssignmentCommand unMarkSecondCommand = new UnMarkAssignmentCommand(INDEX_SECOND_IN_LIST);

        // same object -> returns true
        assertTrue(unMarkFirstCommand.equals(unMarkFirstCommand));

        // same values -> returns true
        UnMarkAssignmentCommand unMarkFirstCommandCopy = new UnMarkAssignmentCommand(INDEX_FIRST_IN_LIST);
        assertTrue(unMarkFirstCommand.equals(unMarkFirstCommandCopy));

        // different types -> returns false
        assertFalse(unMarkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unMarkFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unMarkFirstCommand.equals(unMarkSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        UnMarkAssignmentCommand unMarkCommand = new UnMarkAssignmentCommand(targetIndex);
        String expected = UnMarkAssignmentCommand.class.getCanonicalName() + "{unmark=" + targetIndex + "}";
        assertEquals(expected, unMarkCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no assignment.
     */
    private void showNoAssignment(Model model) {
        model.updateFilteredAssignmentList(p -> false);

        assertTrue(model.getFilteredAssignmentList().isEmpty());
    }
}
