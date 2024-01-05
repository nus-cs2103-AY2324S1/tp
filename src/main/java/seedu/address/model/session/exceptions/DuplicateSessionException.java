package seedu.address.model.session.exceptions;

/**
 * Exception to indicate that an operation would result in duplicate sessions in the session list.
 */
public class DuplicateSessionException extends RuntimeException {

    /**
     * Constructs a DuplicateSessionException with a default error message.
     */
    public DuplicateSessionException() {
        super("Session list contains duplicate session(s).");
    }

    // You can also add additional constructors or methods if needed
}
