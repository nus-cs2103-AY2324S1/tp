package seedu.address.storage.exceptions.storage;

import seedu.address.storage.exceptions.StorageException;

/**
 * Represents an error whereby the user cannot read or write to some system resource
 * for some reason.
 */
public class StorageReadWriteException extends StorageException {
    public StorageReadWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
