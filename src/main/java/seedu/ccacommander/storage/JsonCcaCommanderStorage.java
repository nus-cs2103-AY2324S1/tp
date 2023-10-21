package seedu.ccacommander.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.ccacommander.commons.core.LogsCenter;
import seedu.ccacommander.commons.exceptions.DataLoadingException;
import seedu.ccacommander.commons.exceptions.IllegalValueException;
import seedu.ccacommander.commons.util.FileUtil;
import seedu.ccacommander.commons.util.JsonUtil;
import seedu.ccacommander.model.ReadOnlyCcaCommander;

/**
 * A class to access CcaCommander data stored as a json file on the hard disk.
 */
public class JsonCcaCommanderStorage implements CcaCommanderStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCcaCommanderStorage.class);

    private Path filePath;

    public JsonCcaCommanderStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getCcaCommanderFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCcaCommander> readCcaCommander() throws DataLoadingException {
        return readCcaCommander(filePath);
    }

    /**
     * Similar to {@link #readCcaCommander()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyCcaCommander> readCcaCommander(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableCcaCommander> jsonCcaCommander = JsonUtil.readJsonFile(
                filePath, JsonSerializableCcaCommander.class);
        if (!jsonCcaCommander.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCcaCommander.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveCcaCommander(ReadOnlyCcaCommander addressBook) throws IOException {
        saveCcaCommander(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveCcaCommander(ReadOnlyCcaCommander)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCcaCommander(ReadOnlyCcaCommander addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCcaCommander(addressBook), filePath);
    }

}
