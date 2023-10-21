package seedu.address.logic.parser.exceptions;

/**
 * Represents could not find flag in input.
 */
public class FlagNotFoundException extends ParseException {
    public FlagNotFoundException(String message) {
        super(message);
    }
}
