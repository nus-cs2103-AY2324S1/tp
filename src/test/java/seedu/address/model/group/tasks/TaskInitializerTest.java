package seedu.address.model.group.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.TaskException;

public class TaskInitializerTest {

    @Test
    void initializeTasks_correctlyInitializesTaskList() throws TaskException {
        // Call the method to test
        TaskList taskList = TaskInitializer.initializeTasks();

        // The task list should not be null
        assertNotNull(taskList);

        // Verify the number of tasks created
        assertEquals(13, taskList.getTaskList().size());

        // Verify the correct tasks have been added to the list
        verifyTask(taskList.getTask(0), Todo.class,
            "Upload video of OP1.", TaskStatus.NOT_DONE, TaskModule.CS2101);
        verifyTask(taskList.getTask(1), Todo.class,
            "Complete peer review for OP2.", TaskStatus.NOT_DONE, TaskModule.CS2101);

        // Verify the tasks of type Deadline have correct deadlines
        verifyDeadline(taskList.getTask(2), "(by 29/10/2023 2359)");
    }

    // Helper method to verify the task details
    private void verifyTask(Task task, Class<?> expectedClass, String expectedDescription,
                            TaskStatus expectedStatus, TaskModule expectedModule) {
        assertEquals(expectedClass, task.getClass());
        assertEquals(expectedDescription, task.getTask());
        assertEquals(expectedStatus, task.getStatus());
        assertEquals(expectedModule, task.getModule());
    }

    // Helper method to verify the deadline details
    private void verifyDeadline(Task task, String expectedDeadline) {
        assertEquals(expectedDeadline, ((Deadline) task).getDeadline());
    }
}
