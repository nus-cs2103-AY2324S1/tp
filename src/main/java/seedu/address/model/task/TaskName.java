package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's name in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class TaskName {

    public static final String MESSAGE_CONSTRAINTS =
            "Task Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The task names should ony consist of letters (both uppercase and lowercase),
     * and digits
     */
    public static final String VALIDATION_REGEX = "^(?=.*[A-Za-z0-9])[-A-Za-z0-9\\s]*$";

    public final String taskName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public TaskName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        taskName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return taskName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskName)) {
            return false;
        }

        TaskName otherTaskName = (TaskName) other;
        return taskName.equals(otherTaskName.taskName);
    }

    @Override
    public int hashCode() {
        return taskName.hashCode();
    }

}
