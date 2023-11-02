package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.address.testutil.TypicalTasks.HYDRATION;
import static seedu.address.testutil.TypicalTasks.MEAL;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;

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
        assertTrue(taskManager.hasTask(ASSIGNMENT));
    }

    @Test
    public void addTask_duplicateTask_throwsDuplicateTaskException() {
        taskManager.addTask(ASSIGNMENT);
        assertThrows(DuplicateTaskException.class, () -> taskManager.addTask(ASSIGNMENT));
    }

    @Test
    public void deleteTask_taskFound_success() {
        taskManager.addTask(ASSIGNMENT);
        assertEquals(ASSIGNMENT, taskManager.deleteTask(0));
    }

    @Test
    public void sortDescription_success() {
        taskManager.addTask(ASSIGNMENT);
        taskManager.addTask(MEAL);

        taskManager.sortTasksBy("Deadline");
        taskManager.sortTasksBy("Description");

        assertTrue(taskManager.deleteTask(0).equals(MEAL));
        assertTrue(taskManager.deleteTask(0).equals(ASSIGNMENT));
    }

    @Test
    public void sortDeadline_success() {
        taskManager.addTask(ASSIGNMENT);
        taskManager.addTask(MEAL);

        taskManager.sortTasksBy("Description");
        taskManager.sortTasksBy("Deadline");

        assertTrue(taskManager.deleteTask(0).equals(ASSIGNMENT));
        assertTrue(taskManager.deleteTask(0).equals(MEAL));
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
