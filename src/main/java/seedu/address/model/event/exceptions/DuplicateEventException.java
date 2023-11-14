package seedu.address.model.event.exceptions;

import static seedu.address.logic.Messages.DUPLICATED_EVENTS;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateEventException extends RuntimeException {
    public DuplicateEventException() {
        super(DUPLICATED_EVENTS);
    }
}
