package seedu.address.model.contact.exceptions;

import seedu.address.logic.Messages;

/**
 * Signals that the operation will result in duplicate Contacts (Contacts are considered duplicates if they have the
 * same identity).
 */
public class DuplicateContactException extends RuntimeException {
    public DuplicateContactException() {
        super(Messages.MESSAGE_DUPLICATE_CONTACT_EXCEPTION);
    }
}
