package seedu.address.model.assignment.exceptions;

/**
 * Signals that the operation will result in duplicate Modules (Modules are considered duplicates if they have the same
 * module code).
 */
public class DuplicateAssignmentException extends RuntimeException {
    public DuplicateAssignmentException() {
        super("Operation would result in duplicate assignments");
    }
}
