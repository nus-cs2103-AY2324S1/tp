package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Task in the task list.
 * Guarantees: field values are validated, immutable.
 */
public class Task {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Identity fields
    private final TaskName taskName;

    // Data fields
    private final TaskDescription taskDescription;
    private final TaskPriority priority;
    private final LocalDate date;
    private final TaskProgress progress;

    /**
     * Creates a new task with the given name, description, and completion status.
     *
     * @param taskName        The name of the task. Must not be null.
     * @param taskDescription The description of the task. Must not be null.
     * @param progress        The completion status of the task.
     * @param priority        The level of priority of the task.
     * @param date            The deadline of the task.
     */
    public Task(TaskName taskName, TaskDescription taskDescription,
                TaskPriority priority, LocalDate date, TaskProgress progress) {
        requireAllNonNull(taskName, taskDescription, priority);
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.priority = priority;
        this.date = date;
        this.progress = progress;
    }

    /**
     * Creates a new task with the given name and description.
     * This task is initially marked as not done.
     *
     * @param taskName        The name of the task. Must not be null.
     * @param taskDescription The description of the task. Must not be null.
     * @param priority        The level of priority of the task.
     * @param date            The deadline of the task.
     */
    public Task(TaskName taskName, TaskDescription taskDescription, TaskPriority priority, LocalDate date) {
        requireAllNonNull(taskName, taskDescription, priority);
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.priority = priority;
        this.date = date;
        this.progress = TaskProgress.NOT_STARTED;
    }

    public TaskName getName() {
        return taskName;
    }

    public TaskDescription getDescription() {
        return taskDescription;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public LocalDate getDate() {
        return date;
    }
    public TaskProgress getProgress() {
        return progress;
    }

    /**
     * Returns true if both tasks have the same name and description.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName())
                && otherTask.getDescription().equals(getDescription());
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return taskName.equals(otherTask.taskName)
                && taskDescription.equals(otherTask.taskDescription)
                && priority.equals(otherTask.priority)
                && Objects.equals(date, otherTask.date)
                && progress.equals(otherTask.progress);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskName, taskDescription, priority, date, progress);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", taskName)
                .add("description", taskDescription)
                .add("priority", priority)
                .add("date", date)
                .add("progress", progress)
                .toString();
    }

}
