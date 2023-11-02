package seedu.address.testutil;

import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String DEFAULT_DESCRIPTION = "Submit Assignment";
    public static final String DEFAULT_DEADLINE = "2023-01-01 13:00";

    private TaskDescription description;
    private Deadline deadline;

    /**
     * Creates a TaskBuilder with default description and deadline.
     */
    public TaskBuilder() {
        this.description = new TaskDescription(DEFAULT_DESCRIPTION);
        this.deadline = new Deadline(DEFAULT_DEADLINE);
    }

    /**
     * Creates a task with the given TaskBuilder attributes.
     *
     * @return task corresponding to TaskBuilder's attributes.
     */
    public Task build() {
        return new Task(description, deadline);
    }

    /**
     * Removes the deadline of the task
     *
     * @return TaskBuilder object with an empty deadline.
     */
    public TaskBuilder withNoDeadline() {
        this.deadline = new Deadline((String) null);
        return this;
    }

    /**
     * Sets the deadline of the task to the user input.
     *
     * @param deadline the user input
     * @return TaskBuilder object with deadline changed to user input.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the description of the task to the user input.
     *
     * @param description the user input.
     * @return TaskBuilder object with description changed to user input.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new TaskDescription(description);
        return this;
    }
}
