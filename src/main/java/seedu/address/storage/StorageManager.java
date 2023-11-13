package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyTaskWise;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.exceptions.storage.FileStorageLoadException;

/**
 * Manages storage of TaskWise data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TaskWiseStorage taskWiseStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TaskWiseStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TaskWiseStorage taskWiseStorage, UserPrefsStorage userPrefsStorage) {
        this.taskWiseStorage = taskWiseStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws FileStorageLoadException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ TaskWise methods ==============================

    @Override
    public Path getTaskWiseFilePath() {
        return taskWiseStorage.getTaskWiseFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskWise> readTaskWise() throws FileStorageLoadException {
        return readTaskWise(taskWiseStorage.getTaskWiseFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskWise> readTaskWise(Path filePath) throws FileStorageLoadException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskWiseStorage.readTaskWise(filePath);
    }

    @Override
    public void saveTaskWise(ReadOnlyTaskWise taskWise) throws IOException {
        saveTaskWise(taskWise, taskWiseStorage.getTaskWiseFilePath());
    }

    @Override
    public void saveTaskWise(ReadOnlyTaskWise taskWise, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskWiseStorage.saveTaskWise(taskWise, filePath);
    }

}
