package seedu.codesphere.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javafx.util.Pair;
import seedu.codesphere.commons.exceptions.DataLoadingException;
import seedu.codesphere.model.ReadOnlyCourseList;
import seedu.codesphere.model.ReadOnlyUserPrefs;
import seedu.codesphere.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends CourseListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getCourseListFilePath();

    @Override
    Optional<ReadOnlyCourseList> readCourseList() throws DataLoadingException;

    @Override
    void saveCourseList(ReadOnlyCourseList courseList) throws IOException;

    void addInvalidInput(String text);

    void addValidInput(String text);

    Pair<Boolean, String> getInput();

    Pair<Boolean, String> previousInput();

    Pair<Boolean, String> nextInput();
}
