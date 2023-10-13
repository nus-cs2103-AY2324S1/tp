package seedu.address.model.applicant.exceptions;

/**
 * Signals that the operation is unable to find the specified applicant.
 */
public class DuplicateApplicantException extends RuntimeException {
    public DuplicateApplicantException() {
        super("Operation would result in duplicate applicants");
    }
}
