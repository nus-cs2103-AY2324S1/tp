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
import seedu.address.model.ReadOnlyWellNus;

/**
 * A class to access WellNus data stored as a json file on the hard disk.
 */
public class JsonWellNusStorage implements WellNusStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonWellNusStorage.class);

    private Path filePath;

    public JsonWellNusStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getWellNusFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyWellNus> readWellNus() throws DataLoadingException {
        return readWellNus(filePath);
    }

    /**
     * Similar to {@link #readWellNus()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyWellNus> readWellNus(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableWellNus> jsonWellNus = JsonUtil.readJsonFile(
                filePath, JsonSerializableWellNus.class);
        if (!jsonWellNus.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonWellNus.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveWellNus(ReadOnlyWellNus wellNus) throws IOException {
        saveWellNus(wellNus, filePath);
    }

    /**
     * Similar to {@link #saveWellNus(ReadOnlyWellNus)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveWellNus(ReadOnlyWellNus wellNus, Path filePath) throws IOException {
        requireNonNull(wellNus);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableWellNus(wellNus), filePath);
    }

}
