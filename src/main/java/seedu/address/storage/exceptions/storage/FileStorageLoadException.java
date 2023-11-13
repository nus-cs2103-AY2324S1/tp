package seedu.address.storage.exceptions.storage;

import seedu.address.storage.exceptions.StorageException;

/**
 * Represents an error during loading of data from a file.
 */
public class FileStorageLoadException extends StorageException {
    public FileStorageLoadException(Exception cause) {
        super(cause);
    }

}
