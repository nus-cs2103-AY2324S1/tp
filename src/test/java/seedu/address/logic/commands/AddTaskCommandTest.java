package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK1;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.task.Task;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Task task = new TaskBuilder().build();

        CommandResult commandResult = new AddTaskCommand(task).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, Messages.format(task)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(task), modelStub.tasks);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task task = new TaskBuilder().build();
        AddTaskCommand addCommand = new AddTaskCommand(task);
        ModelStub modelStub = new ModelStubWithTask(task);

        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_DUPLICATE_TASK, () -> addCommand.execute(modelStub));
    }

    @Test
    public void getCommandType() {
        Task task = new TaskBuilder().build();
        AddTaskCommand addCommand = new AddTaskCommand(task);

        assertEquals(addCommand.getCommandType(), CommandType.ADD_TASK);
    }

    @Test
    public void equals() {
        Task task1 = new TaskBuilder().withName("do cs2101").build();
        Task task2 = new TaskBuilder().withName("do cs2100").build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(task1);
        AddTaskCommand addOtherTaskCommand = new AddTaskCommand(task2);

        // same object -> returns true
        assertTrue(addTaskCommand.equals(addTaskCommand));

        // same values -> returns true
        AddTaskCommand addTaskCommandCopy = new AddTaskCommand(task1);
        assertTrue(addTaskCommand.equals(addTaskCommandCopy));

        // different types -> returns false
        assertFalse(addTaskCommand.equals(1));

        // null -> returns false
        assertFalse(addTaskCommand.equals(null));

        // different person -> returns false
        assertFalse(addTaskCommand.equals(addOtherTaskCommand));
    }

    @Test
    public void toStringMethod() {
        AddTaskCommand addCommand = new AddTaskCommand(TASK1);
        String expected = AddTaskCommand.class.getCanonicalName() + "{task=" + TASK1 + "}";
        assertEquals(expected, addCommand.toString());
    }


    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends ModelStub {
        private final Task task;

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
        }

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return this.task.isSameTask(task);
        }
    }

    /**
     * A Model stub that always accept the task being added.
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        final ArrayList<Task> tasks = new ArrayList<>();

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasks.stream().anyMatch(task::isSameTask);
        }

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasks.add(task);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
