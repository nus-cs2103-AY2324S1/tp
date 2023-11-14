package connectify.model;

/**
 * Signals that the operation provided an entity that is invalid.
 */
public class InvalidEntityException extends Exception {
    public InvalidEntityException(String message) {
        super(message);
    }
}
