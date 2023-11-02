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
import seedu.address.model.task.ReadOnlyTaskManager;

/**
 * A class to access TaskManager data stored as a json file on the hard disk.
 */
public class JsonTaskManagerStorage implements TaskManagerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskManagerStorage.class);

    private Path filePath;

    public JsonTaskManagerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskManagerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskManager> readTaskManager() throws DataLoadingException {
        return readTaskManager(filePath);
    }

    /**
     * Similar to {@link #readTaskManager()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyTaskManager> readTaskManager(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskManager> jsonTaskManager = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskManager.class);
        if (!jsonTaskManager.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveTaskManager(ReadOnlyTaskManager taskManager) throws IOException {
        saveTaskManager(taskManager, filePath);
    }

    /**
     * Similar to {@link #saveTaskManager(ReadOnlyTaskManager)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTaskManager(ReadOnlyTaskManager taskManager, Path filePath) throws IOException {
        requireAllNonNull(taskManager, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskManager(taskManager), filePath);
    }

}
