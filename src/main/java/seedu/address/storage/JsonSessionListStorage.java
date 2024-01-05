package seedu.address.storage;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlySessionList;

/**
 * A class to access SessionList data stored as a JSON file on the hard disk.
 */
public class JsonSessionListStorage implements SessionListStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonSessionListStorage.class);
    private Path filePath;

    /**
     * Constructs a JsonSessionListStorage with the specified file path.
     *
     * @param filePath The file path for storing the SessionList data.
     */
    public JsonSessionListStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path of the SessionList data.
     *
     * @return The file path of the SessionList data.
     */
    public Path getSessionListFilePath() {
        return filePath;
    }

    /**
     * Reads the SessionList data from the file specified during construction.
     *
     * @return An {@link Optional} containing the read SessionList data, or an empty
     *         Optional if the file does not exist.
     * @throws DataLoadingException If there is an error reading the data or the
     *         data does not conform to the expected format.
     */
    @Override
    public Optional<ReadOnlySessionList> readSessionList() throws DataLoadingException {
        return readSessionList(filePath);
    }

    /**
     * Reads the SessionList data from the specified file path.
     *
     * @param filePath The file path to read the SessionList data from.
     * @return An {@link Optional} containing the read SessionList data,
     *         or an empty Optional if the file does not exist.
     * @throws DataLoadingException If there is an error reading the data or
     *         the data does not conform to the expected format.
     */
    public Optional<ReadOnlySessionList> readSessionList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableSessionList> jsonSessionList = JsonUtil.readJsonFile(
                filePath, JsonSerializableSessionList.class);
        if (!jsonSessionList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSessionList.get().toModelType());
        } catch (IllegalValueException e) {
            logger.info("Illegal values found in " + filePath + ": " + e.getMessage());
            throw new DataLoadingException(e);
        }
    }

    /**
     * Saves the given SessionList data to the file specified during construction.
     *
     * @param sessionList The SessionList data to be saved.
     * @throws IOException If there is an error saving the data to the file.
     */
    @Override
    public void saveSessionList(ReadOnlySessionList sessionList) throws IOException {
        saveSessionList(sessionList, filePath);
    }

    /**
     * Saves the given SessionList data to the specified file path.
     *
     * @param sessionList The SessionList data to be saved.
     * @param filePath The file path where the data should be saved.
     * @throws IOException If there is an error saving the data to the file.
     */
    public void saveSessionList(ReadOnlySessionList sessionList, Path filePath) throws IOException {
        requireAllNonNull(sessionList, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSessionList(sessionList), filePath);
    }
}
