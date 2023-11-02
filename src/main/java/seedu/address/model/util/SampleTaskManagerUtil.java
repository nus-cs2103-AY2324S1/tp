package seedu.address.model.util;

import seedu.address.model.task.Deadline;
import seedu.address.model.task.ReadOnlyTaskManager;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskManager;

/**
 * Contains utility methods for populating {@code TaskManager} with sample data.
 */
public class SampleTaskManagerUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new TaskDescription("Caffeinate"),
                        new Deadline("2023-11-11 00:00")),
            new Task(new TaskDescription("Buy a new dog"),
                        new Deadline((String) null)),
            new Task(new TaskDescription("Sell textbooks"),
                        new Deadline("2023-12-31 00:00"))
        };
    }

    public static ReadOnlyTaskManager getSampleTaskManager() {
        TaskManager sampleTaskManager = new TaskManager();
        for (Task sampleTask : getSampleTasks()) {
            sampleTaskManager.addTask(sampleTask);
        }
        return sampleTaskManager;
    }

}
