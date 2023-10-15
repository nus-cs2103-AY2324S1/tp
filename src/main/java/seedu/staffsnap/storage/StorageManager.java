package seedu.staffsnap.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.staffsnap.commons.core.LogsCenter;
import seedu.staffsnap.commons.exceptions.DataLoadingException;
import seedu.staffsnap.model.ReadOnlyApplicantBook;
import seedu.staffsnap.model.ReadOnlyUserPrefs;
import seedu.staffsnap.model.UserPrefs;

/**
 * Manages storage of ApplicantBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ApplicantBookStorage applicantBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ApplicantBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ApplicantBookStorage applicantBookStorage, UserPrefsStorage userPrefsStorage) {
        this.applicantBookStorage = applicantBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ApplicantBook methods ==============================

    @Override
    public Path getApplicantBookFilePath() {
        return applicantBookStorage.getApplicantBookFilePath();
    }

    @Override
    public Optional<ReadOnlyApplicantBook> readApplicantBook() throws DataLoadingException {
        return readApplicantBook(applicantBookStorage.getApplicantBookFilePath());
    }

    @Override
    public Optional<ReadOnlyApplicantBook> readApplicantBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return applicantBookStorage.readApplicantBook(filePath);
    }

    @Override
    public void saveApplicantBook(ReadOnlyApplicantBook applicantBook) throws IOException {
        saveApplicantBook(applicantBook, applicantBookStorage.getApplicantBookFilePath());
    }

    @Override
    public void saveApplicantBook(ReadOnlyApplicantBook applicantBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        applicantBookStorage.saveApplicantBook(applicantBook, filePath);
    }

}
