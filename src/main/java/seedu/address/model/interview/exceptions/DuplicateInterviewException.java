package seedu.address.model.interview.exceptions;

/**
 * Signals that the operation will result in duplicate Interviews
 * (Interviews are considered duplicates if they have the same identity - As checked by IsNotValidOrNewInterview).
 */
public class DuplicateInterviewException extends RuntimeException {
    public DuplicateInterviewException() {
        super("Operation would result in duplicate interviews");
    }
}
