package seedu.address.model.person.exceptions;

import seedu.address.logic.Messages;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicatePersonException extends RuntimeException {
    public DuplicatePersonException() {
        super(Messages.MESSAGE_Duplicate_Person_Exception);
    }
}
