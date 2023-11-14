package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_NOT_DONE_TASKS;
import static seedu.address.testutil.TypicalTasks.AGENDA;
import static seedu.address.testutil.TypicalTasks.CATERING;
import static seedu.address.testutil.TypicalTasks.DRAFT;
import static seedu.address.testutil.TypicalTasks.INVITATION;
import static seedu.address.testutil.TypicalTasks.JOURNAL;
import static seedu.address.testutil.TypicalTasks.KPI;
import static seedu.address.testutil.TypicalTasks.LECTURE;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) for {@code FindNotDoneCommand}.
 */
public class FindNotDoneCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_noTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        FindNotDoneCommand command = new FindNotDoneCommand();
        for (Task task : expectedModel.getFilteredTaskList()) {
            expectedModel.markTask(task);
            model.markTask(task);
        }
        expectedModel.updateFilteredTaskList(PREDICATE_SHOW_NOT_DONE_TASKS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 7);
        FindNotDoneCommand command = new FindNotDoneCommand();
        expectedModel.updateFilteredTaskList(PREDICATE_SHOW_NOT_DONE_TASKS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AGENDA, CATERING, DRAFT, INVITATION, JOURNAL, KPI, LECTURE),
                model.getFilteredTaskList());
    }
}
