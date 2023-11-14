package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedLesson.CANNOT_BE_WEEKEND;
import static seedu.address.storage.JsonAdaptedLesson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.availability.FreeTime;
import seedu.address.model.course.Lesson;
import seedu.address.model.course.UniqueCourseList;

public class JsonAdaptedLessonTest {

    @Test
    public void toModelType_validLessonDetails_returnsLesson() throws Exception {
        Lesson modelLesson = UniqueCourseList.findLesson("CS2103T", "Lecture");
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(modelLesson);
        assertEquals(lesson.toModelType(), modelLesson);
    }

    @Test
    public void toModelType_missingName_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(null, "Lecture", "Monday", new JsonAdaptedTimeInterval("12:00-13:00"));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "name");
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_missingCourseCode_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson("CS2103T", null, "Monday", new JsonAdaptedTimeInterval("12:00-13:00"));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "courseCode");
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_missingTimeInterval_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson("CS2103T", "Lecture", "Monday", null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "timeInterval");
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_missingDayOfWeek_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson("CS2103T", "Lecture", null, new JsonAdaptedTimeInterval("12:00-13:00"));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "dayOfWeek");
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_weekendDayOfWeek_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson("CS2103T", "Lecture", "SATURDAY", new JsonAdaptedTimeInterval("12:00-13:00"));
        assertThrows(IllegalValueException.class, CANNOT_BE_WEEKEND, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidTimeInterval_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson("CS2103T", "Lecture", "MONDAY", new JsonAdaptedTimeInterval("13:00-12:00"));
        assertThrows(IllegalValueException.class, FreeTime.MESSAGE_CONSTRAINTS, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidDayOfWeek_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson("CS2103T", "Lecture", "MONDAYY", new JsonAdaptedTimeInterval("12:00-13:00"));
        assertThrows(IllegalArgumentException.class, lesson::toModelType);
    }
}
