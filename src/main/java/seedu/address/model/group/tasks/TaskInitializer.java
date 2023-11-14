package seedu.address.model.group.tasks;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.group.exceptions.TaskException;

/**
 * A utility class for initializing tasks and creating a TaskList.
 */
public class TaskInitializer {
    /**
     * Initializes tasks for different modules and creates a TaskList.
     *
     * @return A TaskList with the initialized tasks.
     * @throws TaskException If there is an issue with task initialization.
     */
    public static TaskList initializeTasks() throws TaskException {
        // Create an empty TaskList
        TaskList taskList = new TaskList();

        List<Task> allTasks = new ArrayList<>();

        // Create tasks for CS2101
        allTasks.add(new Todo("Upload video of OP1.",
            TaskStatus.NOT_DONE, TaskModule.CS2101));
        allTasks.add(new Todo("Complete peer review for OP2.",
            TaskStatus.NOT_DONE, TaskModule.CS2101));
        allTasks.add(new Deadline("Submit slides for OP2.",
            TaskStatus.NOT_DONE, TaskModule.CS2101, "29/10/2023 2359"));
        allTasks.add(new Deadline("Complete peer review.",
            TaskStatus.NOT_DONE, TaskModule.CS2101, "02/11/2023 2359"));
        allTasks.add(new Todo("Research on the SCQA framework.",
            TaskStatus.NOT_DONE, TaskModule.CS2101));
        allTasks.add(new Deadline("Plan for OP2.",
            TaskStatus.NOT_DONE, TaskModule.CS2101, "24/10/2023 2359"));
        allTasks.add(new Deadline("Submit UG.",
            TaskStatus.NOT_DONE, TaskModule.CS2101, "11/11/2023 2359"));

        // Create tasks for CS2103T
        allTasks.add(new Todo("Complete mid semester review form.",
            TaskStatus.NOT_DONE, TaskModule.CS2103T));
        allTasks.add(new Deadline("Add demo screenshots to project notes.",
            TaskStatus.NOT_DONE, TaskModule.CS2103T, "20/11/2023 2359"));
        allTasks.add(new Deadline("Release v1.3.trial jar file.",
            TaskStatus.NOT_DONE, TaskModule.CS2103T, "27/10/2023 2359"));
        allTasks.add(new Deadline("Wrap up milestone 1.3.",
            TaskStatus.NOT_DONE, TaskModule.CS2103T, "03/11/2023 2359"));
        allTasks.add(new Deadline("Finalise TP.",
            TaskStatus.NOT_DONE, TaskModule.CS2103T, "17/11/2023 2359"));
        allTasks.add(new Todo("Update DG for each feature.",
            TaskStatus.NOT_DONE, TaskModule.CS2103T));

        // Add the tasks to the TaskList
        taskList.addTasks(allTasks);
        return taskList;
    }
}
