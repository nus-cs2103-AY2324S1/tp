package seedu.address.model.event.exceptions;

import seedu.address.model.event.Event;

/**
 * Thrown when two events overlap (clash) by time
 */
public class EventOverlapException extends RuntimeException {
    public EventOverlapException(Event e1, Event e2) {
        super("Event " + e1.getUiText() + " overlaps with event " + e2.getUiText());
    }
}
