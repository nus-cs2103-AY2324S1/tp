package seedu.address.model.lessons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.ListEntry;

/**
 * Represents a Task in the application.
 */
public class Task extends ListEntry<Task> {

    public static final String MESSAGE_CONSTRAINTS = "Tasks can take any values, and it should not be blank";

    /*
     * The first character of the task description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    /**
     * The description of the Task.
     */
    protected String description;

    /**
     * The boolean to represent if the Task is Done.
     */
    protected boolean isDone;

    /**
     * Constructs a {@code Task}.
     *
     * @param description A valid description of the task.
     */
    public Task(String description) {
        requireNonNull(description);
        checkArgument(isValidTask(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid task.
     */
    public static boolean isValidTask(String test) {
        return test.matches(VALIDATION_REGEX);
    }



    /**
     * Returns the description of the Task.
     *
     * @return The description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Updates the Description of the Task.
     *
     * @param newDesc The new description of the task.
     */
    public void updateDesc(String newDesc) {
        this.description = newDesc;
    }


    /**
     * Marks the Task as Done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the Task as Not Done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns true if both tasks have the same description.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
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
        return description.equals(otherTask.description);

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description);
    }


    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("description", description)
                .toString();
    }

    @Override
    public Task clone() {
        return new Task(description);
    }
}
