package seedu.address.model.localcourse.exceptions;

/**
 * Signals that the operation will result in duplicate LocalCourses (LocalCourse are considered duplicates if they have
 * the same identity as checked by LocalCourse#isSameLocalCourse).
 */
public class DuplicateLocalCourseException extends RuntimeException {
    public DuplicateLocalCourseException() {
        super("Operation would result in duplicate local courses");
    }
}
