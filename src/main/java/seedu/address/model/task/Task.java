package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTaskName(String)}
 */
public class Task {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String taskName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param taskName A valid tag name.
     */
    public Task(String taskName) {
        requireNonNull(taskName);
        this.taskName = taskName;
    }

    /**
     * Returns true if a given string is a valid tag name.
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

        Task otherTag = (Task) other;
        return taskName.equals(otherTag.taskName);
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
