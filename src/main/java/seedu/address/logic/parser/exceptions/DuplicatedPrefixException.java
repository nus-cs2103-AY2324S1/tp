package seedu.address.logic.parser.exceptions;

/**
 * Represents the error where a prefix is duplicated in the command entered in by the user.
 */
public class DuplicatedPrefixException extends ParseException {
    public DuplicatedPrefixException(String message) {
        super(message);
    }
}
