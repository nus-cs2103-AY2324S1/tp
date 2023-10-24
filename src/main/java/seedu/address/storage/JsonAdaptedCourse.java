package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.course.Course;
import seedu.address.model.course.Lesson;
import seedu.address.model.tag.CourseTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonAdaptedCourse {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Course's %s field is missing!";
    private final String name;
    private final String courseCode;
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCourse} with the given course details.
     */
    @JsonCreator
    public JsonAdaptedCourse(@JsonProperty("name") String name, @JsonProperty("courseCode") String courseCode,
                             @JsonProperty("lessons") List<JsonAdaptedLesson> lessons) {
        this.name = name;
        this.courseCode = courseCode;
        if (lessons != null) {
            this.lessons.addAll(lessons);
        }
    }

    /**
     * Converts a given {@code Course} into this class for Jackson use.
     */
    public JsonAdaptedCourse(Course source) {
        name = source.getName();
        courseCode = source.getCourseCode();
        source.getLessons().forEach(lesson -> lessons.add(new JsonAdaptedLesson(lesson)));
    }

    /**
     * Converts this Jackson-friendly adapted course object into the model's {@code Course} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted course.
     */
    public Course toModelType() throws IllegalValueException {
        final List<Lesson> courseLessons = new ArrayList<>();
        for (JsonAdaptedLesson lesson : this.lessons) {
            courseLessons.add(lesson.toModelType());
        }
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (courseCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "courseCode"));
        }
        if (!CourseTag.isValidCourseCode(courseCode)) {
            throw new IllegalValueException(CourseTag.MESSAGE_CONSTRAINTS);
        }
        final Set<Lesson> modelLessons = new HashSet<>(courseLessons);
        return new Course(name, courseCode, modelLessons);
    }
}
