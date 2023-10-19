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
import seedu.address.model.ReadOnlyManageHr;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonManageHrStorage implements ManageHrStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonManageHrStorage.class);

    private Path filePath;

    public JsonManageHrStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getManageHrFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyManageHr> readManageHr() throws DataLoadingException {
        return readManageHr(filePath);
    }

    /**
     * Similar to {@link #readManageHr()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyManageHr> readManageHr(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableManageHr> jsonManageHR = JsonUtil.readJsonFile(
                filePath, JsonSerializableManageHr.class);
        if (!jsonManageHR.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonManageHR.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveManageHr(ReadOnlyManageHr manageHr) throws IOException {
        saveManageHr(manageHr, filePath);
    }

    /**
     * Similar to {@link #saveManageHr(ReadOnlyManageHr)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveManageHr(ReadOnlyManageHr manageHr, Path filePath) throws IOException {
        requireNonNull(manageHr);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableManageHr(manageHr), filePath);
    }

}
