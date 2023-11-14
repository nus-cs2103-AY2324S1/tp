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
import seedu.address.logic.commands.internship.task.MarkInternshipTaskCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.task.InternshipTask;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkInternshipTaskCommand}.
 */

public class MarkInternshipTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private InternshipTask getAtIndex(int i) {
        return model.getFilteredInternshipTaskList().get(i);
    }

    @Test
    public void execute_mark_success() throws CommandException {
        InternshipTask taskToMark = getAtIndex(INDEX_FIRST_IN_LIST.getZeroBased());
        assertFalse(taskToMark.getStatus().isCompleted());
        MarkInternshipTaskCommand cmd = new MarkInternshipTaskCommand(INDEX_FIRST_IN_LIST);
        cmd.execute(model);
        InternshipTask markedTask = getAtIndex(INDEX_FIRST_IN_LIST.getZeroBased());
        assertTrue(markedTask.getStatus().toString().equals("complete"));
    }


    @Test
    public void execute_markAlreadyCompletedInternshipTask_throwsCommandException() throws CommandException {
        MarkInternshipTaskCommand markCommand = new MarkInternshipTaskCommand(INDEX_FIRST_IN_LIST);
        markCommand.execute(model);
        assertCommandFailure(markCommand, model,
                String.format(MarkInternshipTaskCommand.MESSAGE_TASK_ALREADY_COMPLETE));
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipTaskList().size() + 1);
        MarkInternshipTaskCommand markCommand = new MarkInternshipTaskCommand(outOfBoundIndex);
        CommandAssignmentTestUtil.assertCommandFailure(markCommand, model,
                Messages.MESSAGE_INVALID_INTERNSHIP_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkInternshipTaskCommand markFirstCommand = new MarkInternshipTaskCommand(INDEX_FIRST_IN_LIST);
        MarkInternshipTaskCommand markSecondCommand = new MarkInternshipTaskCommand(INDEX_SECOND_IN_LIST);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkInternshipTaskCommand markFirstCommandCopy = new MarkInternshipTaskCommand(INDEX_FIRST_IN_LIST);
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
        MarkInternshipTaskCommand markCommand = new MarkInternshipTaskCommand(targetIndex);
        String expected = MarkInternshipTaskCommand.class.getCanonicalName() + "{mark-task=" + targetIndex + "}";
        assertEquals(expected, markCommand.toString());
    }
}
