package seedu.ccacommander.model.enrolment.exceptions;

/**
 * Signals that the operation will result in duplicate Enrolment.
 */

public class DuplicateEnrolmentException extends RuntimeException {
    public DuplicateEnrolmentException() {
        super("Operation would result in duplicate enrolments");
    }
}
