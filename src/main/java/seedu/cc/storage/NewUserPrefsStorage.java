package seedu.cc.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.cc.commons.exceptions.DataLoadingException;
import seedu.cc.model.NewReadOnlyUserPrefs;
import seedu.cc.model.NewUserPrefs;

/**
 * Represents a storage for {@link NewUserPrefs}.
 */
public interface NewUserPrefsStorage {

    /**
     * Returns the file path of the UserPrefs data file.
     */
    Path getUserPrefsFilePath();

    /**
     * Returns UserPrefs data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if the loading of data from preference file failed.
     */
    Optional<NewUserPrefs> readUserPrefs() throws DataLoadingException;

    /**
     * Saves the given {@link NewReadOnlyUserPrefs} to the storage.
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserPrefs(NewReadOnlyUserPrefs userPrefs) throws IOException;

}
