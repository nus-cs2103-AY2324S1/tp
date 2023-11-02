package seedu.application.model.job.interview.exceptions;

/**
 * Signals that the operation will result in duplicate Interviews
 * (Interviews are considered duplicates if they have the same interview type, date time and address).
 */
public class DuplicateInterviewException extends RuntimeException {
    public DuplicateInterviewException() {
        super("Operation would result in duplicate interviews");
    }
}
