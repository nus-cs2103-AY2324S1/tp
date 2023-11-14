package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_DONE_TASKS;
import static seedu.address.testutil.TypicalTasks.BUDGET;
import static seedu.address.testutil.TypicalTasks.ENTERTAINMENT;
import static seedu.address.testutil.TypicalTasks.FUNDING;
import static seedu.address.testutil.TypicalTasks.GUESTLIST;
import static seedu.address.testutil.TypicalTasks.HOSPITALITY;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) for {@code FindDoneCommand}.
 */
public class FindDoneCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_noTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        FindDoneCommand command = new FindDoneCommand();
        for (Task task : expectedModel.getFilteredTaskList()) {
            expectedModel.unmarkTask(task);
            model.unmarkTask(task);
        }
        expectedModel.updateFilteredTaskList(PREDICATE_SHOW_DONE_TASKS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 5);
        FindDoneCommand command = new FindDoneCommand();
        expectedModel.updateFilteredTaskList(PREDICATE_SHOW_DONE_TASKS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BUDGET, ENTERTAINMENT, FUNDING, GUESTLIST, HOSPITALITY),
                model.getFilteredTaskList());
    }
}
