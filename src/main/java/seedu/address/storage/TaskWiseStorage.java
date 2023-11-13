package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.ReadOnlyTaskWise;
import seedu.address.storage.exceptions.storage.FileStorageLoadException;

/**
 * Represents a storage for {@link seedu.address.model.TaskWise}.
 */
public interface TaskWiseStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaskWiseFilePath();

    /**
     * Returns TaskWise data as a {@link ReadOnlyTaskWise}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws FileStorageLoadException if loading the data from storage failed.
     */
    Optional<ReadOnlyTaskWise> readTaskWise() throws FileStorageLoadException;

    /**
     * @see #getTaskWiseFilePath()
     */
    Optional<ReadOnlyTaskWise> readTaskWise(Path filePath) throws FileStorageLoadException;

    /**
     * Saves the given {@link ReadOnlyTaskWise} to the storage.
     * @param taskWise cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaskWise(ReadOnlyTaskWise taskWise) throws IOException;

    /**
     * @see #saveTaskWise(ReadOnlyTaskWise)
     */
    void saveTaskWise(ReadOnlyTaskWise taskWise, Path filePath) throws IOException;

}
