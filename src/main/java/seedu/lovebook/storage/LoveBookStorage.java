package seedu.lovebook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.lovebook.commons.exceptions.DataLoadingException;
import seedu.lovebook.model.ReadOnlyLoveBook;

/**
 * Represents a storage for {@link seedu.lovebook.model.LoveBook}.
 */
public interface LoveBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getLoveBookFilePath();

    /**
     * Returns LoveBook data as a {@link ReadOnlyLoveBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyLoveBook> readLoveBook() throws DataLoadingException;

    /**
     * @see #getLoveBookFilePath()
     */
    Optional<ReadOnlyLoveBook> readLoveBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyLoveBook} to the storage.
     * @param loveBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveLoveBook(ReadOnlyLoveBook loveBook) throws IOException;

    /**
     * @see #saveLoveBook(ReadOnlyLoveBook)
     */
    void saveLoveBook(ReadOnlyLoveBook loveBook, Path filePath) throws IOException;

}
