package seedu.address.model.stall.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateStallException extends RuntimeException {
    public DuplicateStallException() {
        super("Operation would result in duplicate stalls");
    }
}
