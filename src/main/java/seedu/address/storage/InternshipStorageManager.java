package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.ReadOnlyInternshipUserPrefs;

/**
 * Manages storage of InternshipBook data in local storage.
 */
public class InternshipStorageManager implements InternshipStorage {

    private static final Logger logger = LogsCenter.getLogger(InternshipStorageManager.class);
    private InternshipBookStorage internshipBookStorage;
    private InternshipUserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code InternshipBookStorage} and {@code UserPrefStorage}.
     */
    public InternshipStorageManager(InternshipBookStorage internshipBookStorage,
                                    InternshipUserPrefsStorage userPrefsStorage) {
        this.internshipBookStorage = internshipBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<InternshipUserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    @Override
    public Path getInternshipBookFilePath() {
        return internshipBookStorage.getInternshipBookFilePath();
    }

    @Override
    public Optional<ReadOnlyInternshipBook> readInternshipBook() throws DataLoadingException {
        return readInternshipBook(internshipBookStorage.getInternshipBookFilePath());
    }

    @Override
    public Optional<ReadOnlyInternshipBook> readInternshipBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return internshipBookStorage.readInternshipBook(filePath);
    }

    @Override
    public void saveInternshipBook(ReadOnlyInternshipBook internshipBook) throws IOException {
        saveInternshipBook(internshipBook, internshipBookStorage.getInternshipBookFilePath());
    }

    /**
     * Saves the given {@link ReadOnlyInternshipBook} to the specified {@link Path}.
     *
     * @param internshipBook The internship book to save. Must not be {@code null}.
     * @param filePath The path of the data file where the internship book should be saved.
     *                 Must not be {@code null}.
     * @throws IOException If there is an issue writing to the specified file path.
     */
    public void saveInternshipBook(ReadOnlyInternshipBook internshipBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        internshipBookStorage.saveInternshipBook(internshipBook, filePath);
    }

}
