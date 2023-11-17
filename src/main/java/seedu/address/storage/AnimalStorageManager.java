package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AnimalReadOnlyUserPrefs;
import seedu.address.model.AnimalUserPrefs;
import seedu.address.model.ReadOnlyAnimalCatalog;

/**
 * {@code AnimalStorageManager} coordinates the storage and retrieval of
 * {@link ReadOnlyAnimalCatalog} data and {@link AnimalUserPrefs} from local storage.
 *
 * This class implements the {@code AnimalStorage} interface and utilizes
 * {@code AnimalCatalogStorage} and {@code AnimalUserPrefsStorage} to perform the actual
 * storage operations. It provides a unified interface to interact with storage
 * functionality related to animal catalogs and user preferences, ensuring that
 * the underlying operations are handled by specialized components.
 *
 * The manager handles tasks such as retrieving data from specified file paths,
 * saving data to files, and managing the file paths used for storage. It acts
 * as a bridge between the application's business logic and its storage infrastructure.
 */
public class AnimalStorageManager implements AnimalStorage {
    private static final Logger logger = LogsCenter.getLogger(AnimalStorageManager.class);

    private final AnimalCatalogStorage animalCatalogStorage;
    private final AnimalUserPrefsStorage userPrefsStorage;

    /**
     * Constructs an {@code AnimalStorageManager} with the given {@code AnimalCatalogStorage} and
     * {@code AnimalUserPrefsStorage}.
     */
    public AnimalStorageManager(AnimalCatalogStorage animalCatalogStorage, AnimalUserPrefsStorage userPrefsStorage) {
        this.animalCatalogStorage = animalCatalogStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ---------------------------------- UserPrefsStorage methods ------------------------------------------

    /**
     * Returns the file path of the UserPrefs file.
     */
    @Override
    public Path getAnimalUserPrefsFilePath() {
        return userPrefsStorage.getAnimalUserPrefsFilePath();
    }

    /**
     * Returns User Prefs data as a {@link AnimalUserPrefs}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    @Override
    public Optional<AnimalUserPrefs> readAnimalUserPrefs() throws DataLoadingException {
        return readAnimalUserPrefs(getAnimalUserPrefsFilePath());
    }

    /**
     * Returns User Prefs data as a {@link AnimalUserPrefs}, read from the given filePath.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    @Override
    public Optional<AnimalUserPrefs> readAnimalUserPrefs(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read user prefs data from file: " + filePath);
        return userPrefsStorage.readAnimalUserPrefs(filePath);
    }

    /**
     * Saves the given {@link AnimalUserPrefsStorage} to the storage.
     *
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveAnimalUserPrefs(AnimalReadOnlyUserPrefs userPrefs) throws IOException {
        logger.fine("Attempting to write user prefs data to file: " + userPrefs);
        userPrefsStorage.saveAnimalUserPrefs(userPrefs);
    }

    // -------------------------------- AnimalCatalogStorage methods --------------------------------------------

    @Override
    public Path getAnimalCatalogFilePath() {
        return animalCatalogStorage.getAnimalCatalogFilePath();
    }

    @Override
    public Optional<ReadOnlyAnimalCatalog> readAnimalCatalog() throws DataLoadingException {
        return readAnimalCatalog(getAnimalCatalogFilePath());
    }

    /**
     * Returns the AnimalCatalog data stored in the specified storage file as a {@link ReadOnlyAnimalCatalog}.
     *
     * @param filePath the storage file to read from.
     * @throws DataLoadingException if loading the data from storage failed.
     * @see #getAnimalCatalogFilePath()
     */
    @Override
    public Optional<ReadOnlyAnimalCatalog> readAnimalCatalog(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return animalCatalogStorage.readAnimalCatalog(filePath);
    }


    @Override
    public void saveAnimalCatalog(ReadOnlyAnimalCatalog readOnlyAnimalCatalog) throws IOException {
        saveAnimalCatalog(readOnlyAnimalCatalog, getAnimalCatalogFilePath());
    }

    /**
     * Saves the given {@link ReadOnlyAnimalCatalog} to the specified storage file.
     *
     * @param readOnlyAnimalCatalog cannot be null.
     * @param filePath               the storage file to be written to.
     * @throws IOException          if there was any problem writing to the file.
     * @see #saveAnimalCatalog(ReadOnlyAnimalCatalog)
     */
    @Override
    public void saveAnimalCatalog(ReadOnlyAnimalCatalog readOnlyAnimalCatalog, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        animalCatalogStorage.saveAnimalCatalog(readOnlyAnimalCatalog, filePath);
    }


}
