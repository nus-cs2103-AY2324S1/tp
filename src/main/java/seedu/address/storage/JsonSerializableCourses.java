package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Courses;
import seedu.address.model.ReadOnlyCourses;
import seedu.address.model.course.Course;

/**
 * An Immutable Courses that is serializable to JSON format.
 */
@JsonRootName(value = "courses")
public class JsonSerializableCourses {
    public static final String MESSAGE_DUPLICATE_COURSE = "Courses list contains duplicate course";
    private final List<JsonAdaptedCourse> courses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableCourses} with the given courses.
     */
    @JsonCreator
    public JsonSerializableCourses(@JsonProperty("courses") List<JsonAdaptedCourse> courses) {
        this.courses.addAll(courses);
    }

    /**
     * Converts a given {@code ReadOnlyCourses} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCourses}.
     */
    public JsonSerializableCourses(ReadOnlyCourses source) {
        courses.addAll(source.getCourseList().stream().map(JsonAdaptedCourse::new).collect(Collectors.toList()));
    }

    /**
     * Converts this courses into the model's {@code Courses} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Courses toModelType() throws IllegalValueException {
        Courses coursesData = new Courses();
        for (JsonAdaptedCourse jsonAdaptedCourse : courses) {
            Course course = jsonAdaptedCourse.toModelType();
            if (coursesData.hasCourse(course)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_COURSE);
            }
            coursesData.addCourse(course);
        }
        return coursesData;
    }
}
