package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyWellNus;
import seedu.address.model.WellNus;

/**
 * Represents a storage for {@link WellNus}.
 */
public interface WellNusStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getWellNusFilePath();

    /**
     * Returns WellNus data as a {@link ReadOnlyWellNus}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyWellNus> readWellNus() throws DataLoadingException;

    /**
     * @see #getWellNusFilePath()
     */
    Optional<ReadOnlyWellNus> readWellNus(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyWellNus} to the storage.
     * @param wellNus cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveWellNus(ReadOnlyWellNus wellNus) throws IOException;

    /**
     * @see #saveWellNus(ReadOnlyWellNus)
     */
    void saveWellNus(ReadOnlyWellNus wellNus, Path filePath) throws IOException;

}
