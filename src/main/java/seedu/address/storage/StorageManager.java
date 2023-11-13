package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyWellNus;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of WellNus data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private WellNusStorage wellNusStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code WellNusStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(WellNusStorage wellNusStorage, UserPrefsStorage userPrefsStorage) {
        this.wellNusStorage = wellNusStorage;
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


    // ================ WellNus methods ==============================

    @Override
    public Path getWellNusFilePath() {
        return wellNusStorage.getWellNusFilePath();
    }

    @Override
    public Optional<ReadOnlyWellNus> readWellNus() throws DataLoadingException {
        return readWellNus(wellNusStorage.getWellNusFilePath());
    }

    @Override
    public Optional<ReadOnlyWellNus> readWellNus(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return wellNusStorage.readWellNus(filePath);
    }

    @Override
    public void saveWellNus(ReadOnlyWellNus wellNus) throws IOException {
        saveWellNus(wellNus, wellNusStorage.getWellNusFilePath());
    }

    @Override
    public void saveWellNus(ReadOnlyWellNus wellNus, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        wellNusStorage.saveWellNus(wellNus, filePath);
    }

}
