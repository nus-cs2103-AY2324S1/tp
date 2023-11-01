package seedu.address.storage;

import java.time.DayOfWeek;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.course.Lesson;

/**
 * TAManager-friendly version of {@link Lesson}.
 */
public class JsonAdaptedLesson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lesson's %s field is missing!";
    private final String name;
    private final String courseCode;
    private final String dayOfWeek;
    private final JsonAdaptedTimeInterval timeInterval;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("name") String name,
                             @JsonProperty("courseCode") String courseCode,
                             @JsonProperty("dayOfWeek") String dayOfWeek,
                             @JsonProperty("interval") JsonAdaptedTimeInterval interval) {
        this.name = name;
        this.courseCode = courseCode;
        this.dayOfWeek = dayOfWeek;
        this.timeInterval = interval;
    }

    /**
     * Converts a given {@code Lesson} into this class for TAManager use.
     */
    public JsonAdaptedLesson(Lesson source) {
        name = source.getName();
        courseCode = source.getCourseCode();
        dayOfWeek = source.getDayOfWeek().toString();
        timeInterval = new JsonAdaptedTimeInterval(source.getTimeInterval());
    }

    /**
     * Converts this TAManager-friendly adapted lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (courseCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "courseCode"));
        }

        if (timeInterval == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "timeInterval"));
        }

        return new Lesson(name,
                courseCode,
                DayOfWeek.valueOf(dayOfWeek),
                timeInterval.toModelType());
    }
}
