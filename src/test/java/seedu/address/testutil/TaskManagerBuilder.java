package seedu.address.testutil;

import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;

/**
 * A utility class to help with building TaskManager objects.
 */
public class TaskManagerBuilder {

    private TaskManager taskManager;

    public TaskManagerBuilder() {
        taskManager = new TaskManager();
    }

    /**
     * Adds a new {@code Task} to the {@code TaskManager} that is being built.
     */
    public TaskManagerBuilder withTask(Task task) {
        taskManager.addTask(task);
        return this;
    }

    public TaskManager build() {
        return taskManager;
    }
}
