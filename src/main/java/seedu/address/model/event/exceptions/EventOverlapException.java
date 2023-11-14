package seedu.address.model.event.exceptions;

import static seedu.address.logic.Messages.EVENT_OVERLAP;

import seedu.address.model.event.Event;

/**
 * Thrown when two events overlap (clash) by time
 */
public class EventOverlapException extends RuntimeException {
    public EventOverlapException(Event e1, Event e2) {
        super(String.format(EVENT_OVERLAP, e1.getUiText(), e2.getUiText()));
    }
}
