package networkbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import networkbook.commons.core.LogsCenter;
import networkbook.commons.exceptions.DataLoadingException;
import networkbook.model.ReadOnlyNetworkBook;
import networkbook.model.ReadOnlyUserPrefs;
import networkbook.model.UserPrefs;

/**
 * Manages storage of NetworkBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private NetworkBookStorage networkBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code NetworkBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(NetworkBookStorage networkBookStorage, UserPrefsStorage userPrefsStorage) {
        this.networkBookStorage = networkBookStorage;
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


    // ================ NetworkBook methods ==============================

    @Override
    public Path getNetworkBookFilePath() {
        return networkBookStorage.getNetworkBookFilePath();
    }

    @Override
    public Optional<ReadOnlyNetworkBook> readNetworkBook() throws DataLoadingException {
        return readNetworkBook(networkBookStorage.getNetworkBookFilePath());
    }

    @Override
    public Optional<ReadOnlyNetworkBook> readNetworkBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return networkBookStorage.readNetworkBook(filePath);
    }

    @Override
    public void saveNetworkBook(ReadOnlyNetworkBook networkBook) throws IOException {
        saveNetworkBook(networkBook, networkBookStorage.getNetworkBookFilePath());
    }

    @Override
    public void saveNetworkBook(ReadOnlyNetworkBook networkBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        networkBookStorage.saveNetworkBook(networkBook, filePath);
    }

}
