package seedu.ccacommander.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.ccacommander.commons.exceptions.DataLoadingException;
import seedu.ccacommander.model.CcaCommander;
import seedu.ccacommander.model.ReadOnlyCcaCommander;

/**
 * Represents a storage for {@link CcaCommander}.
 */
public interface CcaCommanderStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCcaCommanderFilePath();

    /**
     * Returns CcaCommander data as a {@link ReadOnlyCcaCommander}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyCcaCommander> readCcaCommander() throws DataLoadingException;

    /**
     * @see #getCcaCommanderFilePath()
     */
    Optional<ReadOnlyCcaCommander> readCcaCommander(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyCcaCommander} to the storage.
     * @param ccaCommander cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCcaCommander(ReadOnlyCcaCommander ccaCommander) throws IOException;

    /**
     * @see #saveCcaCommander(ReadOnlyCcaCommander)
     */
    void saveCcaCommander(ReadOnlyCcaCommander addressBook, Path filePath) throws IOException;

}
