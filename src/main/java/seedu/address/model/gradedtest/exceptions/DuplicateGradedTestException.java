package seedu.address.model.gradedtest.exceptions;

/**
 * Signals that the operation will result in duplicate Graded Test
 * (Graded Tests are considered duplicates if they have the same identity).
 */
public class DuplicateGradedTestException extends RuntimeException {
    public DuplicateGradedTestException() {
        super("Operation would result in duplicate graded tests");
    }
}
