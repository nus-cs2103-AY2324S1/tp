package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.ReadOnlyTaskWise;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.exceptions.storage.FileStorageLoadException;

/**
 * API of the Storage component
 */
public interface Storage extends TaskWiseStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws FileStorageLoadException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTaskWiseFilePath();

    @Override
    Optional<ReadOnlyTaskWise> readTaskWise() throws FileStorageLoadException;

    @Override
    void saveTaskWise(ReadOnlyTaskWise taskWise) throws IOException;

}
