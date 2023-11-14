package seedu.address.logic.commands.internships.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_IN_LIST;
import static seedu.address.testutil.TypicalInternshipTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandAssignmentTestUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.task.UnMarkInternshipTaskCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.task.InternshipTask;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkAssignmentCommand}.
 */

public class UnMarkInternshipTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private InternshipTask getAtIndex(int i) {
        return model.getFilteredInternshipTaskList().get(i);
    }

    @Test
    public void execute_unMark_success() throws CommandException {
        InternshipTask taskToUnMark = getAtIndex(INDEX_FIRST_IN_LIST.getZeroBased());
        model.setInternshipTask(taskToUnMark, taskToUnMark.mark());
        assertTrue(getAtIndex(INDEX_FIRST_IN_LIST.getZeroBased())
                .getStatus().toString().equals("complete"));
        UnMarkInternshipTaskCommand cmd = new UnMarkInternshipTaskCommand(INDEX_FIRST_IN_LIST);
        cmd.execute(model);
        assertTrue(getAtIndex(INDEX_FIRST_IN_LIST.getZeroBased()).getStatus().toString().equals("incomplete"));
    }

    @Test
    public void execute_unMarkIncompleteInternshipTask_throwsCommandException() {
        InternshipTask taskToUnMark = model.getFilteredInternshipTaskList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        UnMarkInternshipTaskCommand markCommand = new UnMarkInternshipTaskCommand(INDEX_FIRST_IN_LIST);
        model.setInternshipTask(taskToUnMark, taskToUnMark.unMark());
        assertCommandFailure(markCommand, model,
                String.format(UnMarkInternshipTaskCommand.MESSAGE_TASK_ALREADY_INCOMPLETE));
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipTaskList().size() + 1);
        UnMarkInternshipTaskCommand unMarkCommand = new UnMarkInternshipTaskCommand(outOfBoundIndex);

        CommandAssignmentTestUtil.assertCommandFailure(unMarkCommand, model,
                Messages.MESSAGE_INVALID_INTERNSHIP_TASK_DISPLAYED_INDEX);
    }
    @Test
    public void equals() {
        UnMarkInternshipTaskCommand unMarkFirstCommand = new UnMarkInternshipTaskCommand(INDEX_FIRST_IN_LIST);
        UnMarkInternshipTaskCommand unMarkSecondCommand = new UnMarkInternshipTaskCommand(INDEX_SECOND_IN_LIST);

        // same object -> returns true
        assertTrue(unMarkFirstCommand.equals(unMarkFirstCommand));

        // same values -> returns true
        UnMarkInternshipTaskCommand unMarkFirstCommandCopy = new UnMarkInternshipTaskCommand(INDEX_FIRST_IN_LIST);
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
        UnMarkInternshipTaskCommand unMarkCommand = new UnMarkInternshipTaskCommand(targetIndex);
        String expected = UnMarkInternshipTaskCommand.class.getCanonicalName() + "{unmark=" + targetIndex + "}";
        assertEquals(expected, unMarkCommand.toString());
    }
}
