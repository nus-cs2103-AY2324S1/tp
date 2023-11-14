package seedu.address.model.partnercourse.exceptions;

/**
 * Signals that the operation will result in duplicate PartnerCourses (PartnerCourse are considered duplicates
 * if they have the same identity as checked by PartnerCourse#isSamePartnerCourse).
 */
public class DuplicatePartnerCourseException extends RuntimeException {
    public DuplicatePartnerCourseException() {
        super("Operation would result in duplicate partner courses");
    }
}
