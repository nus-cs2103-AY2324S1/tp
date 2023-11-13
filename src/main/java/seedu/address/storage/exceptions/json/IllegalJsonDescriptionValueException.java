package seedu.address.storage.exceptions.json;

/**
 * Represents an error whereby an illegal description was stored and referenced from the
 * JSON save file.
 */
public class IllegalJsonDescriptionValueException extends IllegalJsonValueException {
    public IllegalJsonDescriptionValueException(String message) {
        super(message);
    }
}
