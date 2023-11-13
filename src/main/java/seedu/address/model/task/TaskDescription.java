package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import seedu.address.model.task.exceptions.InvalidTaskDescriptionException;

/**
 * Represents the description of a task.
 */
public class TaskDescription implements Comparable<TaskDescription> {
    public static final String MESSAGE_CONSTRAINTS = "Task description cannot be empty.";
    private final String description;

    /**
     * Constructs a TaskDescription object with the given description.
     *
     * @param description A String representing the description of the task.
     */
    public TaskDescription(String description) {
        requireNonNull(description);
        if (!isValidDescription(description)) {
            throw new InvalidTaskDescriptionException();
        }
        this.description = description;
    }

    /**
     * Checks if the given string is valid as a TaskDescription.
     *
     * @param description the description String to be checked.
     * @return true if the description is valid, false otherwise.
     */
    public static boolean isValidDescription(String description) {
        requireNonNull(description);
        return !description.isEmpty();
    }

    /**
     * Returns the string representation of the description.
     */
    public String get() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TaskDescription)) {
            return false;
        }

        TaskDescription otherTaskDescription = (TaskDescription) other;
        return description.equals(otherTaskDescription.description);
    }

    @Override
    public int compareTo(TaskDescription other) {
        return description.toLowerCase().compareTo(other.description.toLowerCase());
    }
}
