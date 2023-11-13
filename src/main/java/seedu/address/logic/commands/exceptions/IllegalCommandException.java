package seedu.address.logic.commands.exceptions;

/**
 * Represents an error which arises from an illegal command input by the user.
 * <p>
 * Illegal commands are commands that are valid but is attempting to do something
 * that the user has no permission to do.
 */
public class IllegalCommandException extends CommandException {
    public IllegalCommandException(String message) {
        super(message);
    }
}
