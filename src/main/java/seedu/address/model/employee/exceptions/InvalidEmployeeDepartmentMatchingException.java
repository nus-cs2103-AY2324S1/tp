package seedu.address.model.employee.exceptions;

/**
 * Signals that a matching between Department and Employee does not add up
 */
public class InvalidEmployeeDepartmentMatchingException extends ModelException {
    public InvalidEmployeeDepartmentMatchingException() {
        super("The matching between Department and Employees does not add up");
    }
}
