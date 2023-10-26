package networkbook.commons.exceptions;

/**
 * Signals that some state change function call does not fulfill some constraints.
 */
public class IllegalStateChangeException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public IllegalStateChangeException(String message) {
        super(message);
    }

    /**
     * @param message should contain relevant information on the failed constraint(s)
     * @param cause of the main exception
     */
    public IllegalStateChangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
