package seedu.codesphere.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.util.Pair;
import seedu.codesphere.commons.core.LogsCenter;
import seedu.codesphere.commons.exceptions.DataLoadingException;
import seedu.codesphere.model.ReadOnlyCourseList;
import seedu.codesphere.model.ReadOnlyUserPrefs;
import seedu.codesphere.model.UserPrefs;

/**
 * Manages storage of CodeSphere data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final CourseListStorage courseListStorage;
    private final UserPrefsStorage userPrefsStorage;
    private final InputStorage inputStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code CourseListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(CourseListStorage courseListStorage, UserPrefsStorage userPrefsStorage,
                          InputStorage inputStorage) {
        this.courseListStorage = courseListStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.inputStorage = inputStorage;
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


    // ================ CourseList methods ==============================

    @Override
    public Path getCourseListFilePath() {
        return courseListStorage.getCourseListFilePath();
    }

    @Override
    public Optional<ReadOnlyCourseList> readCourseList() throws DataLoadingException {
        return readCourseList(courseListStorage.getCourseListFilePath());
    }

    @Override
    public Optional<ReadOnlyCourseList> readCourseList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return courseListStorage.readCourseList(filePath);
    }

    @Override
    public void saveCourseList(ReadOnlyCourseList courseList) throws IOException {
        saveCourseList(courseList, courseListStorage.getCourseListFilePath());
    }

    @Override
    public void saveCourseList(ReadOnlyCourseList courseList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        courseListStorage.saveCourseList(courseList, filePath);
    }

    // ================ commandStorage methods ==============================

    @Override
    public void addInvalidInput(String text) {
        inputStorage.addInput(false, text);
    }

    @Override
    public void addValidInput(String text) {
        inputStorage.addInput(true, text);
    }

    @Override
    public Pair<Boolean, String> getInput() {
        return inputStorage.getInput();
    }

    @Override
    public Pair<Boolean, String> previousInput() {
        inputStorage.decrementPointer();
        return getInput();
    }

    @Override
    public Pair<Boolean, String> nextInput() {
        inputStorage.incrementPointer();
        return getInput();
    }

}
