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

public class MarkAssignmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Assignment getAtIndex(int i) {
        return model.getFilteredAssignmentList().get(i);
    }

    @Test
    public void execute_mark_success() throws CommandException {
        Assignment assignmentToMark = getAtIndex(INDEX_FIRST_IN_LIST.getZeroBased());
        assertFalse(assignmentToMark.getStatus().isCompleted());
        MarkAssignmentCommand cmd = new MarkAssignmentCommand(INDEX_FIRST_IN_LIST);
        cmd.execute(model);
        Assignment markedAssignment = getAtIndex(INDEX_FIRST_IN_LIST.getZeroBased());
        assertTrue(markedAssignment.getStatus().toString().equals("complete"));
    }


    @Test
    public void execute_markAlreadyCompletedAssignment_throwsCommandException() throws CommandException {
        MarkAssignmentCommand markCommand = new MarkAssignmentCommand(INDEX_FIRST_IN_LIST);
        markCommand.execute(model);
        assertCommandFailure(markCommand, model,
                String.format(MarkAssignmentCommand.MESSAGE_ASSIGNMENT_ALREADY_COMPLETE));
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        MarkAssignmentCommand markCommand = new MarkAssignmentCommand(outOfBoundIndex);
        CommandAssignmentTestUtil.assertCommandFailure(markCommand, model,
                Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkAssignmentCommand markFirstCommand = new MarkAssignmentCommand(INDEX_FIRST_IN_LIST);
        MarkAssignmentCommand markSecondCommand = new MarkAssignmentCommand(INDEX_SECOND_IN_LIST);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkAssignmentCommand markFirstCommandCopy = new MarkAssignmentCommand(INDEX_FIRST_IN_LIST);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        MarkAssignmentCommand markCommand = new MarkAssignmentCommand(targetIndex);
        String expected = MarkAssignmentCommand.class.getCanonicalName() + "{mark=" + targetIndex + "}";
        assertEquals(expected, markCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoAssignment(Model model) {
        model.updateFilteredAssignmentList(p -> false);

        assertTrue(model.getFilteredAssignmentList().isEmpty());
    }
}
