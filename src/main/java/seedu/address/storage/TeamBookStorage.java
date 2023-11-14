package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyTeamBook;

/**
 * Represents a storage for {@link seedu.address.model.TeamBook}.
 */
public interface TeamBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTeamBookFilePath();

    /**
     * Returns TeamBook data as a {@link ReadOnlyTeamBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyTeamBook> readTeamBook() throws DataLoadingException;

    /**
     * @see #getTeamBookFilePath()
     */
    Optional<ReadOnlyTeamBook> readTeamBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyTeamBook} to the storage.
     * @param teamBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTeamBook(ReadOnlyTeamBook teamBook) throws IOException;

    /**
     * @see #saveTeamBook(ReadOnlyTeamBook)
     */
    void saveTeamBook(ReadOnlyTeamBook teamBook, Path filePath) throws IOException;

}
