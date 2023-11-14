package seedu.address.model.employee.exceptions;

/**
 * Signals that the operation is unable to find the specified employee.
 */
public class EmployeeNotFoundException extends ModelException {
    public EmployeeNotFoundException() {
        super("Index out of bound");
    }
}
