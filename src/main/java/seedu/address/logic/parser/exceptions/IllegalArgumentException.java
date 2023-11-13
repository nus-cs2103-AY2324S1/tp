package seedu.address.logic.parser.exceptions;

/**
 * Represents the exception where an illegal argument was entered into the command.
 */
public class IllegalArgumentException extends ParseException {
    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
