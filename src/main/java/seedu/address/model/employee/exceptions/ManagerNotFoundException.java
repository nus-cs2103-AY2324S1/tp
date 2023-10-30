package seedu.address.model.employee.exceptions;

/**
 * Signals that the operation is unable to find the specified supervisor.
 */
public class ManagerNotFoundException extends ModelException {
    public ManagerNotFoundException() {
        super("Some of the specified managers are not assigned to be manager");
    }
}
