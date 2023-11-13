package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER_DG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.FIX_BUG;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskWise;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.TaskBuilder;

public class TaskWiseTest {

    private final TaskWise taskWise = new TaskWise();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskWise.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskWise.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskWise_replacesData() {
        TaskWise newData = getTypicalTaskWise();
        taskWise.resetData(newData);
        assertEquals(newData, taskWise);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedAlice = new TaskBuilder(FIX_BUG).withMembers(VALID_MEMBER_DG)
                .build();
        List<Task> newTasks = Arrays.asList(FIX_BUG, editedAlice);
        TaskWiseStub newData = new TaskWiseStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> taskWise.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskWise.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskWise_returnsFalse() {
        assertFalse(taskWise.hasTask(FIX_BUG));
    }

    @Test
    public void hasTask_taskInTaskWise_returnsTrue() {
        taskWise.addTask(FIX_BUG);
        assertTrue(taskWise.hasTask(FIX_BUG));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInTaskWise_returnsTrue() {
        taskWise.addTask(FIX_BUG);
        Task editedAlice = new TaskBuilder(FIX_BUG).withMembers(VALID_MEMBER_DG)
                .build();
        assertTrue(taskWise.hasTask(editedAlice));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskWise.getTaskList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = TaskWise.class.getCanonicalName() + "{tasks=" + taskWise.getTaskList() + "}";
        assertEquals(expected, taskWise.toString());
    }

    /**
     * A stub ReadOnlyTaskWise whose tasks list can violate interface constraints.
     */
    private static class TaskWiseStub implements ReadOnlyTaskWise {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        TaskWiseStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
