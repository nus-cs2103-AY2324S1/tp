package seedu.letsgethired.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.letsgethired.commons.exceptions.DataLoadingException;
import seedu.letsgethired.model.ReadOnlyInternTracker;
import seedu.letsgethired.model.ReadOnlyUserPrefs;
import seedu.letsgethired.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends InternTrackerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getInternTrackerFilePath();

    @Override
    Optional<ReadOnlyInternTracker> readInternTracker() throws DataLoadingException;

    @Override
    void saveInternTracker(ReadOnlyInternTracker internTracker) throws IOException;

}
