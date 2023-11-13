package seedu.address.logic.commands.exceptions;

/**
 * This represents an error that arises when the user tries to add/edit in a duplicate
 * task to the app.
 * <p>
 * Note to developers: This exception class, and its associated actions may become deprecated in a future
 * release, when we decide to allow duplicated tasks on the task list.
 */
public class DuplicatedTaskException extends CommandException {
    public DuplicatedTaskException(String message) {
        super(message);
    }
}
