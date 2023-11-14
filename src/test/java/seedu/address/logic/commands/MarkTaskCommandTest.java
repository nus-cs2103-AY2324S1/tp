package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkTaskCommand}.
 */
public class MarkTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToMark = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Task markedTask = expectedModel.markTask(taskToMark);

        String expectedMessage = String.format(MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS,
                Messages.format(markedTask));

        assertCommandSuccess(markTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(markTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_markAlreadyMarkedTask_throwsCommandException() {
        Task taskToMark = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        model.markTask(taskToMark);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST);

        assertCommandFailure(markTaskCommand, model, MarkTaskCommand.MESSAGE_HAS_BEEN_MARKED);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST);

        Task taskToMark = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showTaskAtIndex(expectedModel, INDEX_FIRST);
        Task markedTask = expectedModel.markTask(taskToMark);

        String expectedMessage = String.format(MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS,
                Messages.format(markedTask));

        assertCommandSuccess(markTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        MarkTaskCommand markTaskCommand = new MarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(markTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkTaskCommand markTaskFirstCommand = new MarkTaskCommand(INDEX_FIRST);
        MarkTaskCommand markTaskSecondCommand = new MarkTaskCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(markTaskFirstCommand.equals(markTaskFirstCommand));

        // same values -> returns true
        MarkTaskCommand markTaskFirstCommandCopy = new MarkTaskCommand(INDEX_FIRST);
        assertTrue(markTaskFirstCommand.equals(markTaskFirstCommandCopy));

        // different types -> returns false
        assertFalse(markTaskFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markTaskFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(markTaskFirstCommand.equals(markTaskSecondCommand));
    }
    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(targetIndex);
        String expected = MarkTaskCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, markTaskCommand.toString());
    }
}
