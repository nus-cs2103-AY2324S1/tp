package seedu.address.logic.commands.exceptions;

/**
 * Represents an error that arises when the user tries to access a task index
 * that is out of bounds.
 */
public class IllegalTaskIndexException extends IllegalCommandException {
    public IllegalTaskIndexException(String message) {
        super(message);
    }
}
