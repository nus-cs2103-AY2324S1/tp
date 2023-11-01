package seedu.address.model.course.exceptions;

import seedu.address.model.course.UniqueCourseList;

/**
 * Signals that the operation is unable to find the specified course.
 * Will show the user available courses
 */
public class CourseNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Course not found.\n" + UniqueCourseList.getCourseListString();
    }
}
