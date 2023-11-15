package seedu.address.logic.parser.exceptions;

/**
 * Represents a missing index error encountered by a parser.
 */
public class MissingIndexException extends ParseException {
    public MissingIndexException(String message) {
        super(message);
    }
    public MissingIndexException(String message, Throwable cause) {
        super(message, cause);
    }
}
