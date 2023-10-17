package seedu.lovebook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.lovebook.commons.exceptions.DataLoadingException;
import seedu.lovebook.model.ReadOnlyDatePrefs;

/**
 * Represents a storage for {@link seedu.lovebook.model.DatePrefs}.
 */
public interface DatePrefsStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getDatePrefsFilePath();

    /**
     * Returns LoveBook data as a {@link ReadOnlyDatePrefs}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyDatePrefs> readDatePrefs() throws DataLoadingException;

    /**
     * @see #getDatePrefsFilePath()
     */
    Optional<ReadOnlyDatePrefs> readDatePrefs(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyDatePrefs} to the storage.
     * @param prefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDatePrefs(ReadOnlyDatePrefs prefs) throws IOException;

    /**
     * @see #saveDatePrefs(ReadOnlyDatePrefs)
     */
    void saveDatePrefs(ReadOnlyDatePrefs prefs, Path filePath) throws IOException;
}
