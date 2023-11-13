package seedu.letsgethired.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.letsgethired.commons.core.LogsCenter;
import seedu.letsgethired.commons.exceptions.DataLoadingException;
import seedu.letsgethired.commons.exceptions.IllegalValueException;
import seedu.letsgethired.commons.util.FileUtil;
import seedu.letsgethired.commons.util.JsonUtil;
import seedu.letsgethired.model.ReadOnlyInternTracker;

/**
 * A class to access InternTracker data stored as a json file on the hard disk.
 */
public class JsonInternTrackerStorage implements InternTrackerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonInternTrackerStorage.class);

    private Path filePath;

    public JsonInternTrackerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getInternTrackerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyInternTracker> readInternTracker() throws DataLoadingException {
        return readInternTracker(filePath);
    }

    /**
     * Similar to {@link #readInternTracker()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyInternTracker> readInternTracker(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableInternTracker> jsonInternTracker = JsonUtil.readJsonFile(
                filePath, JsonSerializableInternTracker.class);
        if (!jsonInternTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonInternTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveInternTracker(ReadOnlyInternTracker internTracker) throws IOException {
        saveInternTracker(internTracker, filePath);
    }

    /**
     * Similar to {@link #saveInternTracker(ReadOnlyInternTracker)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInternTracker(ReadOnlyInternTracker internTracker, Path filePath) throws IOException {
        requireNonNull(internTracker);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInternTracker(internTracker), filePath);
    }

}
