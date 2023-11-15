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
import seedu.address.model.ReadOnlyPrescriptionList;

/**
 * A class to access CompletedPrescriptionList data stored as a json file on the hard disk.
 */
public class JsonCompletedPrescriptionListStorage implements CompletedPrescriptionListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCompletedPrescriptionListStorage.class);

    private Path filePath;

    public JsonCompletedPrescriptionListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getCompletedPrescriptionListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPrescriptionList> readCompletedPrescriptionList() throws DataLoadingException {
        return readCompletedPrescriptionList(filePath);
    }

    /**
     * Similar to {@link #readCompletedPrescriptionList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyPrescriptionList> readCompletedPrescriptionList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializablePrescriptionList> jsonCompletedPrescriptionList = JsonUtil.readJsonFile(
                filePath, JsonSerializablePrescriptionList.class);
        if (!jsonCompletedPrescriptionList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCompletedPrescriptionList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveCompletedPrescriptionList(ReadOnlyPrescriptionList completedPrescriptionList) throws IOException {
        saveCompletedPrescriptionList(completedPrescriptionList, filePath);
    }

    /**
     * Similar to {@link #saveCompletedPrescriptionList(ReadOnlyPrescriptionList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCompletedPrescriptionList(ReadOnlyPrescriptionList completedPrescriptionList,
            Path filePath) throws IOException {
        requireNonNull(completedPrescriptionList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePrescriptionList(completedPrescriptionList), filePath);
    }

}
