package seedu.address.model.note.exceptions;

/**
 * Signals that the operation is unable to find the specified note.
 */
public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException() {
        super("Note is not found. Please check if the index you input is valid.");
    }
}
