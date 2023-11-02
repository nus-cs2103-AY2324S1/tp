package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javafx.util.Pair;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyCourseList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

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

    void addInvalidCommand(String text);

    void addValidCommand(String text);

    Pair<Boolean, String> getCommand();

    Pair<Boolean, String> previousCommand();

    Pair<Boolean, String> nextCommand();
}
