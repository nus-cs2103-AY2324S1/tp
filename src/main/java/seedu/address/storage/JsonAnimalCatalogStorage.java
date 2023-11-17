package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAnimalCatalog;

/**
 * JsonAnimalCatalogStorage handles the read/write of AnimalCatalog data to local storage.
 * The desired file path for data persistence is supplied to JsonAnimalCatalogStorage upon initialization.
 * Data is then read from/written to the specified file path as a JSON file.
 */
public class JsonAnimalCatalogStorage implements AnimalCatalogStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonAnimalCatalogStorage.class);

    private final Path filePath;

    /**
     * Constructs a {@link JsonAnimalCatalogStorage}.
     *
     * @param filePath the desired file path for storage.
     */
    public JsonAnimalCatalogStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path of the AnimalCatalog file.
     */
    @Override
    public Path getAnimalCatalogFilePath() {
        return filePath;
    }

    /**
     * Returns AnimalCatalog data as a {@link ReadOnlyAnimalCatalog}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    @Override
    public Optional<ReadOnlyAnimalCatalog> readAnimalCatalog() throws DataLoadingException {
        return readAnimalCatalog(filePath);
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
        requireNonNull(filePath);

        Optional<JsonSerializableAnimalCatalog> jsonAnimalCatalog = JsonUtil.readJsonFile(
            filePath, JsonSerializableAnimalCatalog.class);

        if (jsonAnimalCatalog.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAnimalCatalog.get().toAnimalCatalogModel());
        } catch (IllegalValueException e) {
            logger.info(String.format("Illegal values found in %s: %s", filePath, e.getMessage()));
            throw new DataLoadingException(e);
        }
    }

    /**
     * Saves the given {@link ReadOnlyAnimalCatalog} to the storage.
     *
     * @param animalCatalog cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveAnimalCatalog(ReadOnlyAnimalCatalog animalCatalog) throws IOException {
        saveAnimalCatalog(animalCatalog, filePath);
    }

    /**
     * Saves the given {@link ReadOnlyAnimalCatalog} to the specified storage file.
     *
     * @param animalCatalog cannot be null.
     * @param filePath      the storage file to be written to.
     * @throws IOException if there was any problem writing to the file.
     * @see #saveAnimalCatalog(ReadOnlyAnimalCatalog)
     */
    @Override
    public void saveAnimalCatalog(ReadOnlyAnimalCatalog animalCatalog, Path filePath) throws IOException {
        requireNonNull(animalCatalog);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAnimalCatalog(animalCatalog), filePath);
    }
}
