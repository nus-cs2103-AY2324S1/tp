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
 * A class to access PrescriptionList data stored as a json file on the hard disk.
 */
public class JsonPrescriptionListStorage implements PrescriptionListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPrescriptionListStorage.class);

    private Path filePath;

    public JsonPrescriptionListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPrescriptionListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPrescriptionList> readPrescriptionList() throws DataLoadingException {
        return readPrescriptionList(filePath);
    }

    /**
     * Similar to {@link #readPrescriptionList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyPrescriptionList> readPrescriptionList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializablePrescriptionList> jsonPrescriptionList = JsonUtil.readJsonFile(
                filePath, JsonSerializablePrescriptionList.class);
        if (!jsonPrescriptionList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPrescriptionList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void savePrescriptionList(ReadOnlyPrescriptionList prescriptionList) throws IOException {
        savePrescriptionList(prescriptionList, filePath);
    }

    /**
     * Similar to {@link #savePrescriptionList(ReadOnlyPrescriptionList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePrescriptionList(ReadOnlyPrescriptionList prescriptionList, Path filePath) throws IOException {
        requireNonNull(prescriptionList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePrescriptionList(prescriptionList), filePath);
    }

}
