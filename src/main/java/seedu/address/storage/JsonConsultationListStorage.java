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
import seedu.address.model.ReadOnlyConsultationList;

/**
 * A class to access ConsultationList data stored as a JSON file on the hard disk.
 */
public class JsonConsultationListStorage implements ConsultationListStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonConsultationListStorage.class);
    private Path filePath;

    /**
     * Constructs a JsonConsultationListStorage with the specified file path.
     *
     * @param filePath The file path for storing the ConsultationList data.
     */
    public JsonConsultationListStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path of the ConsultationList data.
     *
     * @return The file path of the ConsultationList data.
     */
    @Override
    public Path getConsultationListFilePath() {
        return filePath;
    }

    /**
     * Reads the ConsultationList data from the file specified during construction.
     *
     * @return An {@link Optional} containing the read ConsultationList data, or an empty
     *         Optional if the file does not exist.
     * @throws DataLoadingException If there is an error reading the data or the
     *         data does not conform to the expected format.
     */
    @Override
    public Optional<ReadOnlyConsultationList> readConsultationList() throws DataLoadingException {
        return readConsultationList(filePath);
    }

    /**
     * Reads the ConsultationList data from the specified file path.
     *
     * @param filePath The file path to read the ConsultationList data from.
     * @return An {@link Optional} containing the read ConsultationList data,
     *         or an empty Optional if the file does not exist.
     * @throws DataLoadingException If there is an error reading the data or
     *         the data does not conform to the expected format.
     */
    @Override
    public Optional<ReadOnlyConsultationList> readConsultationList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableConsultationList> jsonConsultationList = JsonUtil.readJsonFile(
                filePath, JsonSerializableConsultationList.class);
        if (!jsonConsultationList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonConsultationList.get().toModelType());
        } catch (IllegalValueException e) {
            logger.info("Illegal values found in " + filePath + ": " + e.getMessage());
            throw new DataLoadingException(e);
        }
    }

    /**
     * Saves the given ConsultationList data to the file specified during construction.
     *
     * @param consultationList The ConsultationList data to be saved.
     * @throws IOException If there is an error saving the data to the file.
     */
    @Override
    public void saveConsultationList(ReadOnlyConsultationList consultationList) throws IOException {
        saveConsultationList(consultationList, filePath);
    }

    /**
     * Saves the given ConsultationList data to the specified file path.
     *
     * @param consultationList The ConsultationList data to be saved.
     * @param filePath The file path where the data should be saved.
     * @throws IOException If there is an error saving the data to the file.
     */
    @Override
    public void saveConsultationList(ReadOnlyConsultationList consultationList, Path filePath) throws IOException {
        requireAllNonNull(consultationList, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableConsultationList(consultationList), filePath);
    }
}
