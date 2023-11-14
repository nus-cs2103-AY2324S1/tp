package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents a Task's status in the task list.
 * Guarantees: immutable; Status can be either {@link TaskStatus#DONE} or {@link TaskStatus#NOT_DONE}.
 */
public class Status {
    public static final Status STATUS_DONE = new Status(TaskStatus.DONE);
    public static final Status STATUS_NOT_DONE = new Status(TaskStatus.NOT_DONE);
    public final TaskStatus taskStatus;

    /**
     * Represents the status of a task in the task list.
     * Each enum constant corresponds to a specific status: {@code DONE} for completed tasks and
     * {@code NOT_DONE} for pending tasks.
     */
    public enum TaskStatus {
        /**
         * Represents a completed task status.
         */
        DONE("Done"),
        /**
         * Represents a pending task status.
         */
        NOT_DONE("Not Done");

        private final String value;

        TaskStatus(String value) {
            this.value = value;
        }
    }

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid status for a task (DONE or NOT_DONE).
     */
    public Status(TaskStatus status) {
        requireNonNull(status);
        this.taskStatus = status;
    }

    @Override
    public String toString() {
        return taskStatus.value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Status)) {
            return false;
        }

        Status otherStatus = (Status) other;
        return taskStatus.equals(otherStatus.taskStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskStatus);
    }

}
