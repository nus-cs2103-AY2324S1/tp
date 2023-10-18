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

    // ================ UserPrefs methods ==============================

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


    // ================ ContactsManager methods ==============================

    @Override
    public Path getConTextFilePath() {
        return contactsStorage.getConTextFilePath();
    }

    @Override
    public Optional<ContactList> readConText() throws DataLoadingException {
        return readConText(contactsStorage.getConTextFilePath());
    }

    @Override
    public Optional<ContactList> readConText(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return contactsStorage.readConText(filePath);
    }

    @Override
    public void saveConText(ContactList contactList) throws IOException {
        saveConText(contactList, contactsStorage.getConTextFilePath());
    }

    @Override
    public void saveConText(ContactList contactList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        contactsStorage.saveConText(contactList, filePath);
    }

}
