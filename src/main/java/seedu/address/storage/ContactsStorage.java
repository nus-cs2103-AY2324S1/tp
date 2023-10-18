package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ContactList;

/**
 * Represents a storage for {@link seedu.address.model.ContactsManager}.
 */
public interface ContactsStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getConTextFilePath();

    /**
     * Returns ContactsManager data as a {@link ContactList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ContactList> readConText() throws DataLoadingException;

    /**
     * @see #getConTextFilePath()
     */
    Optional<ContactList> readConText(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ContactList} to the storage.
     * @param contactList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveConText(ContactList contactList) throws IOException;

    /**
     * @see #saveConText(ContactList)
     */
    void saveConText(ContactList contactList, Path filePath) throws IOException;

}
