package seedu.address.model.group.exceptions;

/**
 * The `TaskException` class represents an exception specific to the Duke program.
 * It is a subclass of the standard Java `Exception` class and is used
 * to handle custom error messages.
 */
public class TaskException extends Exception {

    /**
     * Initializes a new instance of `TaskException` with the specified error message.
     *
     * @param message The error message associated with this exception.
     */
    public TaskException(String message) {
        super(message);
    }
}

