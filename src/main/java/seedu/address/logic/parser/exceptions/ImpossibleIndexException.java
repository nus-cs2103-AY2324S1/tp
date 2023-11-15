package seedu.address.logic.parser.exceptions;

/**
 * Represents a non negative index error encountered by a parser.
 */
public class ImpossibleIndexException extends ParseException {
    public ImpossibleIndexException(String message) {
        super(message);
    }

    public ImpossibleIndexException(String message, Throwable cause) {
        super(message, cause);
    }

}
