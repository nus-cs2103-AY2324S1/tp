package seedu.address.model.lessons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.ListEntryField;


/**
 * Represents a Task in the application.
 */

public class Task extends ListEntryField {

    public static final String MESSAGE_CONSTRAINTS = "Tasks can take any values, and it should not be blank";
    public static final Task DEFAULT_TASK = new Task("Sample Task");

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
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task}, given the done status
     *
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        requireAllNonNull(description, isDone);
        checkArgument(isValidTask(description), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.isDone = isDone;
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

    public boolean isDone() {
        return isDone;
    }

    /**
     * Parses a string task.
     * The first character will be either + or -.
     * If it is +, the task is done. if it is -, the task is undone.
     * @param task
     * @return
     */
    public static Task of(String task) {
        // parse the task
        String description = task.substring(1);
        if (task.charAt(0) == '+') {
            // task done
            return new Task(description, true);
        } else {
            return new Task(description, false);
        }
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
        return (this.isDone ? "+" : "-") + this.description;
    }

    public Task clone() {
        return new Task(this.description, this.isDone);
    }

}
