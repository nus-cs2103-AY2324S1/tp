package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ContactList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;



/**
 * Manages storage of ContactsManager data in local storage.
 */
public class StorageManager implements Storage {
    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);

    private ContactsStorage contactsStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ContactsStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ContactsStorage contactsStorage, UserPrefsStorage userPrefsStorage) {
        this.contactsStorage = contactsStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // [ContactsStorage]

    @Override
    public Path getConTextFilePath() {
        return contactsStorage.getConTextFilePath();
    }

    @Override
    public Optional<ContactList> readContactsManager() throws DataLoadingException {
        return readContactsManager(contactsStorage.getConTextFilePath());
    }

    @Override
    public Optional<ContactList> readContactsManager(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return contactsStorage.readContactsManager(filePath);
    }

    @Override
    public void saveContactsManager(ContactList contactList) throws IOException {
        saveContactsManager(contactList, contactsStorage.getConTextFilePath());
    }

    @Override
    public void saveContactsManager(ContactList contactList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        contactsStorage.saveContactsManager(contactList, filePath);
    }

    // [UserPrefsStorage]

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }
}
