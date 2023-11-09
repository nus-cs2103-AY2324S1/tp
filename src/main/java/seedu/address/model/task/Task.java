package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Task in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTaskName(String)}
 */
public class Task {

    public static final String MESSAGE_CONSTRAINTS = "Task names should be alphanumeric and contain spaces or # only.";
    public static final String VALIDATION_REGEX = "^[\\w\\s#]+$";

    public final String taskName;

    /**
     * Constructs a {@code Task}.
     *
     * @param taskName A valid task name.
     */
    public Task(String taskName) {
        requireNonNull(taskName);
        this.taskName = taskName;
    }

    /**
     * Returns true if a given string is a valid task name.
     *
     * @param test The string to test.
     * @return True if the string is a valid task name, false otherwise.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

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
        return taskName.equals(otherTask.taskName);
    }

    @Override
    public int hashCode() {
        return taskName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + taskName + ']';
    }

}
