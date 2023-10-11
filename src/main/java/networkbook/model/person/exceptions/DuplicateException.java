package networkbook.model.person.exceptions;

/**
 * Signals that an operation will result in a duplicate entry
 * in a list that does not allow duplicates.
 */
public class DuplicateException extends RuntimeException {
    public DuplicateException() {
        super("Operation would result in duplicate items");
    }
}
