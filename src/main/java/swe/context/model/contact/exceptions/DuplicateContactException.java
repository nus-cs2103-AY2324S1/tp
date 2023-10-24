package swe.context.model.contact.exceptions;

import swe.context.logic.Messages;

/**
 * Signals that the operation will result in duplicate Contacts (Contacts are considered duplicates if they have the
 * same identity).
 */
public class DuplicateContactException extends RuntimeException {
    public DuplicateContactException() {
        super(Messages.DUPLICATE_CONTACT_EXCEPTION);
    }
}
