package seedu.address.model.student.grades.exceptions;

import seedu.address.logic.Messages;

/**
 * Signals that the tutorial index is invalid.
 */
public class InvalidTutorialIndexException extends RuntimeException {
    public InvalidTutorialIndexException() {
        super(Messages.MESSAGE_INVALID_TUTORIAL_INDEX);
    }
}
