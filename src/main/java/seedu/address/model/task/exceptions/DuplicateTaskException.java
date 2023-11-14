package seedu.address.model.task.exceptions;

/**
 * Signals that the operation will result in duplicate Tasks.
 * Tasks are considered duplicates if they have the same Title and Note.
 */
public class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException() {
        super("Operation would result in duplicate tasks");
    }
}
