package seedu.address.model.event.exceptions;

public class ConflictingEventException extends RuntimeException {
    public ConflictingEventException() {
        super("Operation would result in events with overlapping timeframe");
    }
}
