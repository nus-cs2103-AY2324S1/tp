package seedu.address.storage.exceptions.json;

/**
 * Represents an error whereby a duplicated task is stored and loaded from the
 * JSON save file.
 */
public class IllegalJsonDuplicatedTaskException extends IllegalJsonValueException {
    public IllegalJsonDuplicatedTaskException(String message) {
        super(message);
    }
}
