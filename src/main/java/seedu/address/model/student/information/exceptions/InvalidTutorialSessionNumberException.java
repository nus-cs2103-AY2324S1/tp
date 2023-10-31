package seedu.address.model.student.information.exceptions;

import seedu.address.model.student.ClassDetails;

/**
 * Signals that the tutorial session number is invalid.
 */
public class InvalidTutorialSessionNumberException extends RuntimeException {
    public InvalidTutorialSessionNumberException() {
        super(ClassDetails.MESSAGE_INVALID_TUTORIAL_SESSION_NUMBER);
    }
}
