package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlySessionList;

/**
 * Represents a storage for {@link seedu.address.model.SessionListBook}.
 */
public interface SessionListStorage {
    /**
     * Returns the file path of the data file.
     *
     * @return The file path of the data file.
     */
    Path getSessionListFilePath();

    /**
     * Reads the session list data from the default file path.
     *
     * @return An {@link Optional} containing the read {@link ReadOnlySessionList},
     *         or an empty {@link Optional} if no data is found.
     * @throws DataLoadingException If there is an error reading the data from the file.
     */
    Optional<ReadOnlySessionList> readSessionList() throws DataLoadingException;

    /**
     * Reads the session list data from the specified file path.
     *
     * @param filePath The file path from which to read the session list data.
     * @return An {@link Optional} containing the read {@link ReadOnlySessionList},
     *         or an empty {@link Optional} if no data is found.
     * @throws DataLoadingException If there is an error reading the data from the file.
     */
    Optional<ReadOnlySessionList> readSessionList(Path filePath) throws DataLoadingException;

    /**
     * Saves the session list data to the default file path.
     *
     * @param sessionList The {@link ReadOnlySessionList} to be saved.
     * @throws IOException If there is an error writing the data to the file.
     */
    void saveSessionList(ReadOnlySessionList sessionList) throws IOException;

    /**
     * Saves the session list data to the specified file path.
     *
     * @param sessionList The {@link ReadOnlySessionList} to be saved.
     * @param filePath   The file path to which the data should be saved.
     * @throws IOException If there is an error writing the data to the file.
     */
    void saveSessionList(ReadOnlySessionList sessionList, Path filePath) throws IOException;
}
