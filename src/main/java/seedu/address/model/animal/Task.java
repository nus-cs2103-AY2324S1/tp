package seedu.address.model.animal;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents the Task addressed to the {@link Animal}
 */
public class Task {

    public static final String MESSAGE_CONSTRAINTS = "Description should not be empty, "
            + "and should not contain only whitespaces ";
    public static final String VALIDATION_REGEX = "^.*\\S.*$";

    private final boolean isDone;
    private final String description;


    /**
     * Constructs a Task Object. isDone status of tasks are initialized to be false.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        requireNonNull(description);
        checkArgument(isValidTask(description), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task Object. This method is only used as the constructor when converting
     * from a Jackson-friendly adapted Task to an actual Task Object.
     *
     * @param description Description of the task.
     * @param isDone isDone status of the task.
     */
    public Task(String description, boolean isDone) {
        requireNonNull(description);
        checkArgument(isValidTask(description), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task.
     *
     * @return A boolean value that indicates whether a task has been done.
     */
    public boolean checkIsDone() {
        return isDone;
    }

    /**
     * Checks whether the description of the task is valid using the regex expression. A task should not be empty,
     * and should not only contain whitespace characters.
     *
     * @param test Description of task to be tested.
     * @return Boolean value that indicates whether a task is valid.
     */
    public static boolean isValidTask(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public Task updateTask(boolean isDone) {
        return new Task(description, isDone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task that = (Task) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    /**
     * Returns the status of the task from the getStatusIcon enclosed within square brackets,
     * along with a description of the task.
     *
     * @return A string containing Task status and description of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", (isDone ? "X" : " "), description);
    }

}
