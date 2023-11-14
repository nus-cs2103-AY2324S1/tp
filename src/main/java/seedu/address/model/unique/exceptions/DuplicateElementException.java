package seedu.address.model.unique.exceptions;

/**
 * Thrown when there are duplicates
 */
public class DuplicateElementException extends RuntimeException {
    public DuplicateElementException() {
        super("Operation would result in duplicates");
    }
}
