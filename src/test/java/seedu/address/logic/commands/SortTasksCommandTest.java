package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskManager;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;


class SortTasksCommandTest {
    private static final String COMPARATOR_TYPE_DESCRIPTION = "Description";
    private static final String COMPARATOR_TYPE_DEADLINE = "Deadline";
    private static final String COMPARATOR_TYPE_INVALID = "Invalid";
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCalendar(), getTypicalTaskManager(),
            new UserPrefs());

    @Test
    public void equals() {
        SortTasksCommand sortTasksByDescriptionCommand = new SortTasksCommand(COMPARATOR_TYPE_DESCRIPTION);
        SortTasksCommand sortTasksByDeadlineCommand = new SortTasksCommand(COMPARATOR_TYPE_DEADLINE);
        SortTasksCommand invalidSortTasksCommand = new SortTasksCommand(COMPARATOR_TYPE_INVALID);
        Object notSortTasksCommand = new Object();

        assertTrue(sortTasksByDescriptionCommand.equals(sortTasksByDescriptionCommand));

        assertFalse(sortTasksByDeadlineCommand.equals(sortTasksByDescriptionCommand));

        assertFalse(invalidSortTasksCommand.equals(sortTasksByDeadlineCommand));

        assertFalse(sortTasksByDescriptionCommand.equals(notSortTasksCommand));

        assertFalse(sortTasksByDescriptionCommand.equals(null));
    }

    @Test
    public void execute_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new SortTasksCommand(COMPARATOR_TYPE_DESCRIPTION).execute(null));
    }

    @Test
    public void execute_invalidType_throwsCommandException() {
        assertThrows(CommandException.class, () ->
                new SortTasksCommand(COMPARATOR_TYPE_INVALID).execute(model));
    }

    @Test
    public void execute_validType_success() throws CommandException {
        new SortTasksCommand(COMPARATOR_TYPE_DESCRIPTION).execute(model);
        assertTrue(model.getTaskManager().getSortingOrder().equals(new Task.TaskDescriptorComparator()));
    }

    @Test
    public void toStringMethod() {
        SortTasksCommand sortTasksCommand = new SortTasksCommand(COMPARATOR_TYPE_DESCRIPTION);
        String expected = SortTasksCommand.class.getCanonicalName() + "{comparatorType=" + COMPARATOR_TYPE_DESCRIPTION
                + "}";
        assertEquals(expected, sortTasksCommand.toString());
    }
}
