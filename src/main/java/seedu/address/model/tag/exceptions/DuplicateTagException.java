package seedu.address.model.tag.exceptions;

/**
 * Represents an exception that is thrown when an operation would result in duplicate tags.
 */
public class DuplicateTagException extends RuntimeException {

    /**
     * Constructs a DuplicateTagException with a default error message.
     * The error message indicates that the operation would result in duplicate tags.
     */
    public DuplicateTagException() {
        super("Operation would result in duplicate tags");
    }
}
