package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.AGENDA;
import static seedu.address.testutil.TypicalTasks.CATERING;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

public class DeleteAllDoneCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allDoneTask_emptyAddressBook() {
        for (Task task : model.getFilteredTaskList()) {
            model.markTask(task);
        }
        expectedModel.setAddressBook(new AddressBook());
        assertCommandSuccess(new DeleteAllDoneCommand(), model,
                DeleteAllDoneCommand.MESSAGE_DELETE_ALL_DONE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_someDoneTask_nonEmptyAddressBook() {
        for (Task task : model.getFilteredTaskList()) {
            expectedModel.unmarkTask(task);
            model.unmarkTask(task);
        }
        List<Task> tasksToMarkAndDelete = Arrays.asList(AGENDA, CATERING);
        for (Task task : tasksToMarkAndDelete) {
            expectedModel.markTask(task);
            model.markTask(task);
        }
        expectedModel.deleteAllDone();
        assertCommandSuccess(new DeleteAllDoneCommand(), model,
                DeleteAllDoneCommand.MESSAGE_DELETE_ALL_DONE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_noDoneTask_throwsCommandException() {
        for (Task task : model.getFilteredTaskList()) {
            expectedModel.unmarkTask(task);
            model.unmarkTask(task);
        }
        assertCommandFailure(new DeleteAllDoneCommand(), model, DeleteAllDoneCommand.MESSAGE_NO_DONE_TASKS);
        assertEquals(expectedModel.getFilteredTaskList(), model.getFilteredTaskList());
    }

}
