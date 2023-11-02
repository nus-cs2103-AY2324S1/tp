package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.task.ReadOnlyTaskManager;

/**
 * Represents a storage for {@link seedu.address.model.task.TaskManager}.
 */
public interface TaskManagerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaskManagerFilePath();

    /**
     * Returns TaskManager data as a {@link ReadOnlyTaskManager}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyTaskManager> readTaskManager() throws DataLoadingException;

    /**
     * @see #getTaskManagerFilePath()
     */
    Optional<ReadOnlyTaskManager> readTaskManager(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyTaskManager} to the storage.
     * @param taskManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaskManager(ReadOnlyTaskManager taskManager) throws IOException;

    /**
     * @see #saveTaskManager(ReadOnlyTaskManager)
     */
    void saveTaskManager(ReadOnlyTaskManager taskManager, Path filePath) throws IOException;

}
