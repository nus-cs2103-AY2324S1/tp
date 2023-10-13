package seedu.application.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.application.commons.exceptions.DataLoadingException;
import seedu.application.model.ReadOnlyApplicationBook;

/**
 * Represents a storage for {@link seedu.application.model.ApplicationBook}.
 */
public interface ApplicationBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getApplicationBookFilePath();

    /**
     * Returns ApplicationBook data as a {@link ReadOnlyApplicationBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyApplicationBook> readApplicationBook() throws DataLoadingException;

    /**
     * @see #getApplicationBookFilePath()
     */
    Optional<ReadOnlyApplicationBook> readApplicationBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyApplicationBook} to the storage.
     * @param applicationBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveApplicationBook(ReadOnlyApplicationBook applicationBook) throws IOException;

    /**
     * @see #saveApplicationBook(ReadOnlyApplicationBook)
     */
    void saveApplicationBook(ReadOnlyApplicationBook applicationBook, Path filePath) throws IOException;

}
