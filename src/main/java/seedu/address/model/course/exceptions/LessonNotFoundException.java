package seedu.address.model.course.exceptions;

/**
 * Signals that the operation is unable to find the specified lesson.
 */
public class LessonNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Lesson not found.";
    }
}
