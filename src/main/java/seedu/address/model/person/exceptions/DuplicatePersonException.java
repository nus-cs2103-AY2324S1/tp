package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicatePersonException extends RuntimeException {
    /**
     * Constructs a new {@code DuplicatePersonException} with the specified detail {@code message}.
     */
    public DuplicatePersonException() {
        super("Operation would result in duplicate persons");
    }
}
