package networkbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import networkbook.commons.exceptions.DataLoadingException;
import networkbook.model.NetworkBook;
import networkbook.model.ReadOnlyNetworkBook;

/**
 * Represents a storage for {@link NetworkBook}.
 */
public interface NetworkBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getNetworkBookFilePath();

    /**
     * Returns NetworkBook data as a {@link ReadOnlyNetworkBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyNetworkBook> readNetworkBook() throws DataLoadingException;

    /**
     * @see #getNetworkBookFilePath()
     */
    Optional<ReadOnlyNetworkBook> readNetworkBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyNetworkBook} to the storage.
     * @param networkBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveNetworkBook(ReadOnlyNetworkBook networkBook) throws IOException;

    /**
     * @see #saveNetworkBook(ReadOnlyNetworkBook)
     */
    void saveNetworkBook(ReadOnlyNetworkBook networkBook, Path filePath) throws IOException;

}
