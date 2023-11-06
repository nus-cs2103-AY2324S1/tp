package networkbook.commons.exceptions;

/**
 * Represents a checked exception thrown when an object field that is not supposed to be null is null.
 * This happens when reading from JSON.
 */
public class NullValueException extends IllegalValueException {
    public NullValueException() {
        super("Non-nullable field(s) should not be null");
    }
}
