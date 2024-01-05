package seedu.address.model.session.exceptions;

/**
 * Exception to indicate that a requested session was not found in the session list.
 */
public class SessionNotFoundException extends RuntimeException {

    /**
     * Constructs a SessionNotFoundException with a default error message.
     */
    public SessionNotFoundException() {
        super("Session not found");
    }

    // You can also add additional constructors or methods if needed
}
