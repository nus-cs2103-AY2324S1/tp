package seedu.letsgethired.model.application.exceptions;

/**
 * Signals that the operation will result in duplicate InternApplications (InternApplications are considered
 * duplicates
 * if they have the same
 * identity).
 */
public class DuplicateInternApplicationException extends RuntimeException {
    public DuplicateInternApplicationException() {
        super("Operation would result in duplicate intern applications");
    }
}
