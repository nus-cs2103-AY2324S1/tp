package seedu.address.model.musician.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateMusicianException extends RuntimeException {
    public DuplicateMusicianException() {
        super("Operation would result in duplicate persons");
    }
}
