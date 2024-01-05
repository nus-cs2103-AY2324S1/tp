package seedu.address.model.gradedtest.exceptions;

/**
 * Signals that the operation is unable to find the specified gradedTest.
 */
public class GradedTestNotFoundException extends RuntimeException {
    public GradedTestNotFoundException() {
        super("Graded Test not found.");
    }
}
