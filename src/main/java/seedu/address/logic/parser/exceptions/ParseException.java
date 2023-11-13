package seedu.address.logic.parser.exceptions;

/**
 * Represents a parse error encountered by a parser. This represents the superclass of all other parsing-related
 * errors that may arise from using this app.
 */
public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
