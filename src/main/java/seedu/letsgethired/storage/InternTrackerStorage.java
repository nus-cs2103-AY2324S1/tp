package seedu.letsgethired.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.letsgethired.commons.exceptions.DataLoadingException;
import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.ReadOnlyInternTracker;

/**
 * Represents a storage for {@link InternTracker}.
 */
public interface InternTrackerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInternTrackerFilePath();

    /**
     * Returns InternTracker data as a {@link ReadOnlyInternTracker}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyInternTracker> readInternTracker() throws DataLoadingException;

    /**
     * @see #getInternTrackerFilePath()
     */
    Optional<ReadOnlyInternTracker> readInternTracker(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyInternTracker} to the storage.
     * @param internTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInternTracker(ReadOnlyInternTracker internTracker) throws IOException;

    /**
     * @see #saveInternTracker(ReadOnlyInternTracker)
     */
    void saveInternTracker(ReadOnlyInternTracker internTracker, Path filePath) throws IOException;

}
