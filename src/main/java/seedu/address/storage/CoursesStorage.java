package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyCourses;

/**
 * Represents a storage for {@link seedu.address.model.Courses}.
 */
public interface CoursesStorage {

    /**
     * Returns the file path of the Courses data file.
     */
    Path getCoursesFilePath();

    /**
     * Returns Courses data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if the loading of data from preference file failed.
     */
    Optional<ReadOnlyCourses> readCourses() throws DataLoadingException;

    /**
     * Saves the given {@link seedu.address.model.ReadOnlyUserPrefs} to the storage.
     * @param courses cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCourses(ReadOnlyCourses courses) throws IOException;

    void saveCourses(ReadOnlyCourses courses, Path filePath) throws IOException;
}
