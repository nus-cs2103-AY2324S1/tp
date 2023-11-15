package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.course.Course;

/**
 * Jackson-friendly version of {@link Course}.
 */
class JsonAdaptedCourse {

    private final String courseName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code courseName}.
     */
    @JsonCreator
    public JsonAdaptedCourse(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Converts a given {@code Course} into this class for Jackson use.
     */
    public JsonAdaptedCourse(Course source) {
        courseName = source.courseName;
    }

    @JsonValue
    public String getCourseName() {
        return courseName;
    }

    /**
     * Converts this Jackson-friendly adapted course object into the model's {@code Course} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted course.
     */
    public Course toModelType() throws IllegalValueException {
        if (!Course.isValidCourseName(courseName)) {
            throw new IllegalValueException(Course.MESSAGE_CONSTRAINTS);
        } else if (!Course.isExistingCourseName(courseName)) {
            throw new IllegalValueException(Course.MESSAGE_INVALID_COURSE);
        }
        return new Course(courseName);
    }

}
