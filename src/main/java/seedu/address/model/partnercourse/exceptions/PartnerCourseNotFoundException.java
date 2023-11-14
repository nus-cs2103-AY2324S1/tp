package seedu.address.model.partnercourse.exceptions;

/**
 * Signals that the operation is unable to find the specified PartnerCourse.
 */
public class PartnerCourseNotFoundException extends RuntimeException {
    public PartnerCourseNotFoundException() {
        super("Partner course does not exist");
    }
}
