package seedu.address.model.task;

import org.junit.jupiter.api.Test;
import seedu.address.model.task.exceptions.DuplicateTaskException;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.address.testutil.TypicalTasks.HYDRATION;

class TaskManagerTest {
    private final TaskManager taskManager = new TaskManager();

    @Test
    public void constructor() {
        assertEquals(new TaskList().asUnmodifiableList(), taskManager.getTaskList());
    }

    @Test
    public void deleteTask_emptyTaskList_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskManager.deleteTask(1));
    }

    @Test
    public void addTask_emptyTaskList_success() {
        taskManager.addTask(ASSIGNMENT);
        assertTrue(taskManager.getTaskList().contains(ASSIGNMENT));
    }

    @Test
    public void addTask_duplicateTask_throwsDuplicateTaskException() {
        taskManager.addTask(ASSIGNMENT);
        assertThrows(DuplicateTaskException.class, () -> taskManager.addTask(ASSIGNMENT));
    }

    @Test
    public void deleteTask_taskFound_success() {
        taskManager.addTask(ASSIGNMENT);
        assertEquals(ASSIGNMENT, taskManager.deleteTask(1));
    }

    @Test
    public void equalsMethod() {
        taskManager.addTask(ASSIGNMENT);
        TaskManager equivalentTaskManager = new TaskManager();
        equivalentTaskManager.addTask(ASSIGNMENT);
        TaskManager emptyTaskManager = new TaskManager();
        TaskManager differentTaskManager = new TaskManager();
        differentTaskManager.addTask(HYDRATION);
        Object notTaskManager = new Object();

        assertTrue(taskManager.equals(taskManager));

        assertTrue(taskManager.equals(equivalentTaskManager));

        assertFalse(taskManager.equals(emptyTaskManager));

        assertFalse(taskManager.equals(notTaskManager));
    }
}