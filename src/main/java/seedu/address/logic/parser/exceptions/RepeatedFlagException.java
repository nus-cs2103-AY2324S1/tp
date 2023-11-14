package seedu.address.logic.parser.exceptions;
/**
 * Represents repeated flag in input.
 */
public class RepeatedFlagException extends ParseException {
    public RepeatedFlagException(String message) {
        super(message);
    }
}
