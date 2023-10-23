package seedu.address.logic.parser.exceptions;

/**
 * Represents a parse error encountered by a parser due to invalid input.
 */
public class InvalidInputException extends ParseException {
    public InvalidInputException(String message) {
        super(message);
    }
}
