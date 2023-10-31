package seedu.address.model.employee.exceptions;

/**
 * Signals that the operation is unable to find the specified supervisor.
 */
public class SupervisorNotFoundException extends ModelException {
    public SupervisorNotFoundException() {
        super("Some of the specified managers are not assigned to be manager");
    }
}
