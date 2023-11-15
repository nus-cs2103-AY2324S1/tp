package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate objects.
 */
public class DuplicateObjectException extends RuntimeException {
    public DuplicateObjectException() {
        super("Operation would result in duplicate objects");
    }
}
