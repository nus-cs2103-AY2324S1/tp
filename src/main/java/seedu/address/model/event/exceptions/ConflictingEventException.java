package seedu.address.model.event.exceptions;

/**
 * Signals that the operation will result in events that overlap in their time frame.
 */
public class ConflictingEventException extends RuntimeException {
    public ConflictingEventException() {
        super("Operation would result in events with overlapping timeframe");
    }
}
