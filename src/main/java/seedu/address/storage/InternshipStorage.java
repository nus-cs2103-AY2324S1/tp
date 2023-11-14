package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.ReadOnlyInternshipUserPrefs;

/**
 * API of the Storage component
 */
public interface InternshipStorage extends InternshipBookStorage, InternshipUserPrefsStorage {

    @Override
    Optional<InternshipUserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) throws IOException;

    @Override
    Path getInternshipBookFilePath();

    @Override
    Optional<ReadOnlyInternshipBook> readInternshipBook() throws DataLoadingException;

    @Override
    void saveInternshipBook(ReadOnlyInternshipBook internshipBook) throws IOException;

    @Override
    void saveInternshipBook(ReadOnlyInternshipBook internshipBook, Path filePath) throws IOException;
}
