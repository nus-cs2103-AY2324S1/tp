package seedu.address.model.applicant.exceptions;

public class DuplicateApplicantException extends RuntimeException {
    public DuplicateApplicantException() {
        super("Operation would result in duplicate applicants");
    }
}
