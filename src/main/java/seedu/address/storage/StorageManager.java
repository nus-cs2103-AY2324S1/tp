package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyConText;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ConText data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ConTextStorage ConTextStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ConTextStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ConTextStorage ConTextStorage, UserPrefsStorage userPrefsStorage) {
        this.ConTextStorage = ConTextStorage;
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


    // ================ ConText methods ==============================

    @Override
    public Path getConTextFilePath() {
        return ConTextStorage.getConTextFilePath();
    }

    @Override
    public Optional<ReadOnlyConText> readConText() throws DataLoadingException {
        return readConText(ConTextStorage.getConTextFilePath());
    }

    @Override
    public Optional<ReadOnlyConText> readConText(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return ConTextStorage.readConText(filePath);
    }

    @Override
    public void saveConText(ReadOnlyConText ConText) throws IOException {
        saveConText(ConText, ConTextStorage.getConTextFilePath());
    }

    @Override
    public void saveConText(ReadOnlyConText ConText, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        ConTextStorage.saveConText(ConText, filePath);
    }

}
