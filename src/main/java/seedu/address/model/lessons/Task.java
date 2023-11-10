package seedu.address.model.lessons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ListEntryField;



/**
 * Represents a Task in the application.
 */

public class Task extends ListEntryField {

    public static final String MESSAGE_CONSTRAINTS = "Tasks can take any values, and it should not be blank";
    public static final String DECODED_CONSTRAINTS = "Incorrect task encoding! The encoded task should have a \"+\" "
            + "or \"-\" at the beginning of the string,";
    public static final Task DEFAULT_TASK = new Task("Sample Task");

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
        if (!isValidTask(description)) {
            throw new IllegalArgumentException();
        }
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task}, given the done status
     *
     * @param description A valid description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        requireAllNonNull(description, isDone);
        description = description.trim();
        if (!isValidTask(description)) {
            throw new IllegalArgumentException();
        }
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns true if a given string is a valid task.
     */
    public static boolean isValidTask(String test) {
        return !test.trim().isEmpty();
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
     *
     * Defaults to undone if not present.
     *
     * @param task
     * @return Task
     * @throws ParseException if the string doesn't contain a + or - at the start.
     */
    public static Task ofDepreciated(String task) throws ParseException {
        // parse the task
        checkArgument(isValidEncodedTask(task));
        String description = task.substring(1);
        if (task.charAt(0) == '+') {
            // task done
            return new Task(description, true);
        } else if (task.charAt(0) == '-') {
            return new Task(description, false);
        } else {
            throw new ParseException(DECODED_CONSTRAINTS);
        }
    }
    public static Task of(String task) throws ParseException {
        return new Task(task);
    }

    /**
     * Tests if a encoded string is valid.
     * @param test The test string
     * @return
     */
    public static boolean isValidEncodedTask(String test) {
        // first character must be + or -
        return (test.charAt(0) == '+' || test.charAt(0) == '-') && isValidTask(test.substring(1));
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
        return description.equals(otherTask.description) && isDone == otherTask.isDone;

    }

    /**
     * Serializes the task into a string for storage.
     * @return serialized string
     */
    public String serialize() {
        return (this.isDone ? "+" : "-") + this.description;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Returns a clone of this task that is equal to this task.
     */
    public Task clone() {
        return new Task(description);
    }
}
