package seedu.staffsnap.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.staffsnap.commons.exceptions.DataLoadingException;
import seedu.staffsnap.model.ReadOnlyApplicantBook;

/**
 * Represents a storage for {@link seedu.staffsnap.model.ApplicantBook}.
 */
public interface ApplicantBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getApplicantBookFilePath();

    /**
     * Returns ApplicantBook data as a {@link ReadOnlyApplicantBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyApplicantBook> readApplicantBook() throws DataLoadingException;

    /**
     * @see #getApplicantBookFilePath()
     */
    Optional<ReadOnlyApplicantBook> readApplicantBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyApplicantBook} to the storage.
     * @param applicantBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveApplicantBook(ReadOnlyApplicantBook applicantBook) throws IOException;

    /**
     * @see #saveApplicantBook(ReadOnlyApplicantBook)
     */
    void saveApplicantBook(ReadOnlyApplicantBook applicantBook, Path filePath) throws IOException;

}
