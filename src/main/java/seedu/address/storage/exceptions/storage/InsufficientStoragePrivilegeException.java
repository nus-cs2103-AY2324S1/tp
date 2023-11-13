package seedu.address.storage.exceptions.storage;

import seedu.address.storage.exceptions.StorageException;

/**
 * Represents an exception whereby the user does not have sufficient
 * storage privilege to access a certain file system resource.
 */
public class InsufficientStoragePrivilegeException extends StorageException {
    public InsufficientStoragePrivilegeException(String message, Throwable cause) {
        super(message, cause);
    }
}
