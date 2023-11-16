package seedu.address.model.person.exceptions;

/**
 * Exception thrown when a client cannot be found in a specific operation or context.
 */
public class ClientNotFoundException extends RuntimeException {

    /**
     * Constructs a `ClientNotFoundException` with a default error message.
     * The message indicates that a client cannot be found in the operation or context.
     */
    public ClientNotFoundException() {
        super("Unable to find client in operation");
    }
}
