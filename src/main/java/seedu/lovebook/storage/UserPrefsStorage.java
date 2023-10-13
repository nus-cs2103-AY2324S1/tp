package seedu.lovebook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.lovebook.commons.exceptions.DataLoadingException;
import seedu.lovebook.model.ReadOnlyUserPrefs;
import seedu.lovebook.model.UserPrefs;

/**
 * Represents a storage for {@link seedu.lovebook.model.UserPrefs}.
 */
public interface UserPrefsStorage {

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
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    /**
     * Saves the given {@link seedu.lovebook.model.ReadOnlyUserPrefs} to the storage.
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

}
