package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ManageHr;
import seedu.address.model.ReadOnlyManageHR;

/**
 * Represents a storage for {@link ManageHr}.
 */
public interface ManageHrStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getManageHrFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyManageHR}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyManageHR> readManageHR() throws DataLoadingException;

    /**
     * @see #getManageHrFilePath()
     */
    Optional<ReadOnlyManageHR> readManageHR(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyManageHR} to the storage.
     * @param manageHR cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveManageHR(ReadOnlyManageHR manageHR) throws IOException;

    /**
     * @see #saveManageHR(ReadOnlyManageHR)
     */
    void saveManageHR(ReadOnlyManageHR manageHR, Path filePath) throws IOException;

}
