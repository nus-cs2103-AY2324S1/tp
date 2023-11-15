package seedu.address.model.displayable.exceptions;

/**
 * Signals that the operation will result in duplicate displayables (Displayables are
 * considered duplicates if they have the same identity).
 */
public class DuplicateException extends RuntimeException {
    public DuplicateException() {
        super("Operation would result in duplicates");
    }
}
