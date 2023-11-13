package seedu.address.logic.parser.exceptions;

/**
 * Represents the error thrown when the app receives a command that it does not understand.
 */
public class InvalidCommandException extends ParseException {
    public InvalidCommandException(String message, String errString) {
        super(String.format(message, errString));
    }
}
