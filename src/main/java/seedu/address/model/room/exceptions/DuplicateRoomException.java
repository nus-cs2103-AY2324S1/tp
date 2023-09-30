package seedu.address.model.room.exceptions;

/**
 * Signals that the operation will result in duplicate room (Persons are considered duplicates if they have the same
 * room number).
 */
public class DuplicateRoomException extends RuntimeException {
    public DuplicateRoomException() {
        super("Operation would result in duplicate room");
    }
}
