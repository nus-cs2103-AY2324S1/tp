package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyManageHr;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ManageHrStorage manageHrStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ManageHrStorage manageHrStorage, UserPrefsStorage userPrefsStorage) {
        this.manageHrStorage = manageHrStorage;
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


    // ================ ManageHR methods ==============================

    @Override
    public Path getManageHrFilePath() {
        return manageHrStorage.getManageHrFilePath();
    }

    @Override
    public Optional<ReadOnlyManageHr> readManageHr() throws DataLoadingException {
        return readManageHr(manageHrStorage.getManageHrFilePath());
    }

    @Override
    public Optional<ReadOnlyManageHr> readManageHr(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return manageHrStorage.readManageHr(filePath);
    }

    @Override
    public void saveManageHr(ReadOnlyManageHr manageHr) throws IOException {
        saveManageHr(manageHr, manageHrStorage.getManageHrFilePath());
    }

    @Override
    public void saveManageHr(ReadOnlyManageHr manageHr, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        manageHrStorage.saveManageHr(manageHr, filePath);
    }

}
