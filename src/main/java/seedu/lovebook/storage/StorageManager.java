package seedu.lovebook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.lovebook.commons.core.LogsCenter;
import seedu.lovebook.commons.exceptions.DataLoadingException;
import seedu.lovebook.model.ReadOnlyLoveBook;
import seedu.lovebook.model.ReadOnlyUserPrefs;
import seedu.lovebook.model.UserPrefs;

/**
 * Manages storage of LoveBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private LoveBookStorage loveBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code LoveBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(LoveBookStorage loveBookStorage, UserPrefsStorage userPrefsStorage) {
        this.loveBookStorage = loveBookStorage;
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


    // ================ LoveBook methods ==============================

    @Override
    public Path getLoveBookFilePath() {
        return loveBookStorage.getLoveBookFilePath();
    }

    @Override
    public Optional<ReadOnlyLoveBook> readLoveBook() throws DataLoadingException {
        return readLoveBook(loveBookStorage.getLoveBookFilePath());
    }

    @Override
    public Optional<ReadOnlyLoveBook> readLoveBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return loveBookStorage.readLoveBook(filePath);
    }

    @Override
    public void saveLoveBook(ReadOnlyLoveBook loveBook) throws IOException {
        saveLoveBook(loveBook, loveBookStorage.getLoveBookFilePath());
    }

    @Override
    public void saveLoveBook(ReadOnlyLoveBook loveBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        loveBookStorage.saveLoveBook(loveBook, filePath);
    }

}
