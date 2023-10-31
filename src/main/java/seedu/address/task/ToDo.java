package seedu.address.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    final String TASK_CHAR = "[T]";

    /**
     * Creates a new ToDo task with the given taskName
     *
     * @param taskName The name of the task
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return TASK_CHAR + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + super.getStatusNumber() + " | " + super.getTaskName();
    }

}
