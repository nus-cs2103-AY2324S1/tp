package seedu.address.model.course.exceptions;

/**
 * Signals that the operation is unable to find the specified course.
 */
public class CourseNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Course not found.";
    }
}
