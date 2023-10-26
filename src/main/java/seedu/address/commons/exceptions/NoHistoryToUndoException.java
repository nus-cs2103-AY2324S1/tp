package seedu.address.commons.exceptions;

/**
 * Thrown when the user tries to Undo while there are no histories
 */
public class NoHistoryToUndoException extends RuntimeException {
    public NoHistoryToUndoException() {
        super("State pointer already at start, nothing to undo");
    }
}
