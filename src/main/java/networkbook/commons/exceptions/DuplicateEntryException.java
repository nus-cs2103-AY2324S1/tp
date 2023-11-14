package networkbook.commons.exceptions;

/**
 * Represents a checked error when there are duplicate entries within a list.
 */
public class DuplicateEntryException extends Exception {
    public DuplicateEntryException(String message) {
        super(message);
    }
}
