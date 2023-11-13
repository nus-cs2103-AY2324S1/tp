package seedu.address.model.tutorial.exceptions;
// @@author Propene-Dan
/**
 * Signals that the operation will result in duplicate Tutorials (Tutorials are considered duplicates if they have the
 * same module code).
 */
public class DuplicateTutorialException extends RuntimeException {
    public DuplicateTutorialException() {
        super("Operation would result in duplicate tutorials");
    }
}
