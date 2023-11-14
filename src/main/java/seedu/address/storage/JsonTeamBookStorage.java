package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTeamBook;

/**
 * A class to handle storage of TeamBook data in JSON format.
 */
public class JsonTeamBookStorage implements TeamBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTeamBookStorage.class);

    private Path filePath;

    /**
     * Constructs a {@code JsonTeamBookStorage} with the given file path.
     *
     * @param filePath File path to the JSON storage file.
     */
    public JsonTeamBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getTeamBookFilePath() {
        return filePath;
    }

    /**
     * Reads the team book data from the default storage file.
     *
     * @return An optional of ReadOnlyTeamBook.
     *         Returns {@code Optional.empty()} if the file is not found.
     * @throws DataLoadingException If there is a problem with reading the data from the storage file.
     */
    @Override
    public Optional<ReadOnlyTeamBook> readTeamBook() throws DataLoadingException {
        return readTeamBook(filePath);
    }

    /**
     * Reads the team book data from the specified storage file.
     *
     * @param filePath File path to the JSON storage file.
     * @return An optional of ReadOnlyTeamBook.
     *         Returns {@code Optional.empty()} if the file is not found.
     * @throws DataLoadingException If there is a problem with reading the data from the storage file.
     */
    @Override
    public Optional<ReadOnlyTeamBook> readTeamBook(Path filePath) throws DataLoadingException {
        try {
            Optional<JsonSerializableTeamBook> jsonTeamBook = JsonUtil.readJsonFile(
                    filePath, JsonSerializableTeamBook.class);
            if (!jsonTeamBook.isPresent()) {
                return Optional.empty();
            }
            return Optional.of(jsonTeamBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    /**
     * Saves the given team book data to the default storage file.
     *
     * @param teamBook The team book data. Must not be null.
     * @throws IOException If there was any problem writing to the storage file.
     */
    @Override
    public void saveTeamBook(ReadOnlyTeamBook teamBook) throws IOException {
        saveTeamBook(teamBook, filePath);
    }

    /**
     * Saves the given team book data to the specified storage file.
     *
     * @param teamBook The team book data. Must not be null.
     * @param filePath File path to the JSON storage file.
     * @throws IOException If there was any problem writing to the storage file.
     */
    @Override
    public void saveTeamBook(ReadOnlyTeamBook teamBook, Path filePath) throws IOException {
        JsonUtil.saveJsonFile(new JsonSerializableTeamBook(teamBook), filePath);
    }
}
