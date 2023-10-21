package seedu.ccacommander.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.ccacommander.commons.core.LogsCenter;
import seedu.ccacommander.commons.exceptions.DataLoadingException;
import seedu.ccacommander.model.ReadOnlyCcaCommander;
import seedu.ccacommander.model.ReadOnlyUserPrefs;
import seedu.ccacommander.model.UserPrefs;

/**
 * Manages storage of CcaCommander data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private CcaCommanderStorage ccaCommanderStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code CcaCommanderStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(CcaCommanderStorage ccaCommanderStorage, UserPrefsStorage userPrefsStorage) {
        this.ccaCommanderStorage = ccaCommanderStorage;
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


    // ================ CcaCommander methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return ccaCommanderStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyCcaCommander> readAddressBook() throws DataLoadingException {
        return readAddressBook(ccaCommanderStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyCcaCommander> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return ccaCommanderStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyCcaCommander addressBook) throws IOException {
        saveAddressBook(addressBook, ccaCommanderStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyCcaCommander addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        ccaCommanderStorage.saveAddressBook(addressBook, filePath);
    }

}
