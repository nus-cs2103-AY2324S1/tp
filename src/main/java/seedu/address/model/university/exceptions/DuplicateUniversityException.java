package seedu.address.model.university.exceptions;

/**
 * Signals that the operation will result in duplicate Universities (University are considered duplicates if they have
 * the same identity as checked by University#isSameUniversity).
 */
public class DuplicateUniversityException extends RuntimeException {
    public DuplicateUniversityException() {
        super("Operation would result in duplicate universities");
    }
}
