package seedu.address.logic.parser.exceptions;

/**
 * Represents an error whereby there are no recorded modifications made to an object
 * that expects to be modified.
 */
public class NoRecordedModificationException extends ParseException {
    public NoRecordedModificationException(String message) {
        super(message);
    }
}
