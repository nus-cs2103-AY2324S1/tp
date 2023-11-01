package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AddressBookManager;
import seedu.address.model.ReadOnlyAddressBookManager;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link AddressBookManager}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<AddressBookManager> readAddressBooks() throws DataLoadingException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<AddressBookManager> readAddressBooks(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link AddressBookManager} to the storage.
     * @param addressBookManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBooks(ReadOnlyAddressBookManager addressBookManager) throws IOException;

    /**
     * @see #saveAddressBook(AddressBookManager)
     */
    void saveAddressBooks(ReadOnlyAddressBookManager addressBookManager, Path filePath) throws IOException;

}
