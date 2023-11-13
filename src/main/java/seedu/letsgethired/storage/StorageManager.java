package seedu.letsgethired.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.letsgethired.commons.core.LogsCenter;
import seedu.letsgethired.commons.exceptions.DataLoadingException;
import seedu.letsgethired.model.ReadOnlyInternTracker;
import seedu.letsgethired.model.ReadOnlyUserPrefs;
import seedu.letsgethired.model.UserPrefs;

/**
 * Manages storage of InternTracker data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private InternTrackerStorage internTrackerStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code InternTrackerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(InternTrackerStorage internTrackerStorage, UserPrefsStorage userPrefsStorage) {
        this.internTrackerStorage = internTrackerStorage;
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


    // ================ InternTracker methods ==============================

    @Override
    public Path getInternTrackerFilePath() {
        return internTrackerStorage.getInternTrackerFilePath();
    }

    @Override
    public Optional<ReadOnlyInternTracker> readInternTracker() throws DataLoadingException {
        return readInternTracker(internTrackerStorage.getInternTrackerFilePath());
    }

    @Override
    public Optional<ReadOnlyInternTracker> readInternTracker(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return internTrackerStorage.readInternTracker(filePath);
    }

    @Override
    public void saveInternTracker(ReadOnlyInternTracker internTracker) throws IOException {
        saveInternTracker(internTracker, internTrackerStorage.getInternTrackerFilePath());
    }

    @Override
    public void saveInternTracker(ReadOnlyInternTracker internTracker, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        internTrackerStorage.saveInternTracker(internTracker, filePath);
    }

}
