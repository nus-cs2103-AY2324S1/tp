package seedu.address.logic.parser.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {

    /**
     * Constructs a ParseException with a specified error message.
     *
     * @param message The error message that describes the parse error.
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * Constructs a ParseException with a specified error message and the cause of the error.
     *
     * @param message The error message that describes the parse error.
     * @param cause   The cause of the parse error.
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
