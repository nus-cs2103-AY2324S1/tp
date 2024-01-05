package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's description in the task list.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDescription(String)}
 */
public class TaskDescription {

    public static final String MESSAGE_CONSTRAINTS = "Description should be less than 100 characters.";
    public static final String VALIDATION_REGEX = "^.{0,100}$";

    public final String description;

    /**
     * Constructs a {@code Tag}.
     *
     * @param description A valid description.
     */
    public TaskDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid task description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskDescription)) {
            return false;
        }

        TaskDescription otherTaskDescription = (TaskDescription) other;
        return description.equals(otherTaskDescription.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return description.equals("") ? "No Description Provided" : description;
    }

}

