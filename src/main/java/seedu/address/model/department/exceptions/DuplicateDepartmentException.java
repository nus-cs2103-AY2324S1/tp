package seedu.address.model.department.exceptions;

/**
 * Signals that the operation will result in duplicate Departments (Departments are considered
 * duplicates if they have the same identity).
 */
public class DuplicateDepartmentException extends RuntimeException {
    public DuplicateDepartmentException() {
        super("Operation would result in duplicate department");
    }
}
