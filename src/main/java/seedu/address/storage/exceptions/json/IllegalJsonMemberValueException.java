package seedu.address.storage.exceptions.json;

/**
 * Represents an error whereby an illegal member name was stored and referenced from the
 * JSON save file.
 */
public class IllegalJsonMemberValueException extends IllegalJsonValueException {
    public IllegalJsonMemberValueException(String message) {
        super(message);
    }
}
