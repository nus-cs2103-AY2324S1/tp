package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyInternshipBook;

/**
 * Represents a storage for {@link seedu.address.model.InternshipBook}.
 */
public interface InternshipBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInternshipBookFilePath();

    /**
     * Returns InternshipBook data as a {@link ReadOnlyInternshipBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyInternshipBook> readInternshipBook() throws DataLoadingException;

    /**
     * @see #getInternshipBookFilePath()
     */
    Optional<ReadOnlyInternshipBook> readInternshipBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyInternshipBook} to the storage.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInternshipBook(ReadOnlyInternshipBook internshipBook) throws IOException;

    /**
     * @see #saveInternshipBook(ReadOnlyInternshipBook)
     */
    void saveInternshipBook(ReadOnlyInternshipBook internshipBook, Path filePath) throws IOException;

}
