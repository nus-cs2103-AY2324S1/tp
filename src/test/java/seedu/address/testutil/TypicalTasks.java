package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.Task;
import seedu.address.model.task.TaskManager;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests
 */
public class TypicalTasks {
    public static final Task ASSIGNMENT = new TaskBuilder()
            .withDescription("Submit Assignment")
            .withDeadline("2023-11-15 08:30").build();

    public static final Task HYDRATION = new TaskBuilder()
            .withDescription("Drink more water!")
            .withNoDeadline().build();

    public static final Task MEAL = new TaskBuilder()
            .withDescription("Lunch")
            .withDeadline("2023-11-15 15:00").build();

    // Manually added
    public static final Task REPORT = new TaskBuilder()
            .withDescription("Finish Report")
            .withNoDeadline().build();

    public static final Task FEATURE = new TaskBuilder()
            .withDescription("Push Feature")
            .withDeadline("2023-11-15 15:00").build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns a {@code TaskManager} with all typical tasks.
     */
    public static TaskManager getTypicalTaskManager() {
        TaskManager taskManager = new TaskManager();
        for (Task task : getTypicalTasks()) {
            taskManager.addTask(task);
        }
        return taskManager;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ASSIGNMENT, HYDRATION, MEAL));
    }
}
