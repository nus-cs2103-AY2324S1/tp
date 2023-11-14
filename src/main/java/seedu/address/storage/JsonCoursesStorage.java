package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.course.Course;


/**
 * A class to access Courses data stored as a json file on the hard disk.
 */
public class JsonCoursesStorage implements CoursesStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonCoursesStorage.class);
    private final Path filePath;

    public JsonCoursesStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getCoursesFilePath() {
        return filePath;
    }

    @Override
    public Optional<List<Course>> readCourses() throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableCourses> jsonCoursesData = JsonUtil.readJsonFile(
                filePath, JsonSerializableCourses.class);
        if (!jsonCoursesData.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCoursesData.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        } catch (IllegalArgumentException iae) {
            logger.info("Illegal arguments found in " + filePath + ": " + iae.getMessage());
            throw new DataLoadingException(iae);
        } catch (DateTimeParseException dtpe) {
            logger.info("Illegal date format found in " + filePath + ": " + dtpe.getMessage());
            throw new DataLoadingException(dtpe);
        }
    }

    @Override
    public void saveCourses(List<Course> courses) throws IOException, DataLoadingException {
        saveCourses(courses, filePath);
    }

    /**
     * Similar to .
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCourses(List<Course> courses, Path filePath) throws IOException, DataLoadingException {
        requireNonNull(courses);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCourses(courses.toArray(new Course[0])), filePath);
    }

}
