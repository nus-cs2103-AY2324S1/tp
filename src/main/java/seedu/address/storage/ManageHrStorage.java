package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ManageHr;
import seedu.address.model.ReadOnlyManageHr;

/**
 * Represents a storage for {@link ManageHr}.
 */
public interface ManageHrStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getManageHrFilePath();

    /**
     * Returns ManageHR data as a {@link ReadOnlyManageHr}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyManageHr> readManageHr() throws DataLoadingException;

    /**
     * @see #getManageHrFilePath()
     */
    Optional<ReadOnlyManageHr> readManageHr(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyManageHr} to the storage.
     * @param manageHr cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveManageHr(ReadOnlyManageHr manageHr) throws IOException;

    /**
     * @see #saveManageHr(ReadOnlyManageHr)
     */
    void saveManageHr(ReadOnlyManageHr manageHr, Path filePath) throws IOException;

}
