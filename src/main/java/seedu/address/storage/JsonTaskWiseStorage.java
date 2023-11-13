package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTaskWise;
import seedu.address.storage.exceptions.json.IllegalJsonValueException;
import seedu.address.storage.exceptions.storage.FileStorageLoadException;

/**
 * A class to access TaskWise data stored as a json file on the hard disk.
 */
public class JsonTaskWiseStorage implements TaskWiseStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskWiseStorage.class);

    private Path filePath;

    public JsonTaskWiseStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskWiseFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskWise> readTaskWise() throws FileStorageLoadException {
        return readTaskWise(filePath);
    }

    /**
     * Similar to {@link #readTaskWise()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws FileStorageLoadException if loading the data from storage failed.
     */
    public Optional<ReadOnlyTaskWise> readTaskWise(Path filePath) throws FileStorageLoadException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskWise> jsonTaskWise = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskWise.class);

        if (jsonTaskWise.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskWise.get().toModelType());
        } catch (IllegalJsonValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new FileStorageLoadException(ive);
        }
    }

    @Override
    public void saveTaskWise(ReadOnlyTaskWise addressBook) throws IOException {
        saveTaskWise(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveTaskWise(ReadOnlyTaskWise)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTaskWise(ReadOnlyTaskWise addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskWise(addressBook), filePath);
    }

}
