package seedu.address.model.employee.exceptions;

/**
 * Signals that the operation is unable to assign the specified role.
 */
public class InvalidRoleException extends ModelException {
    public InvalidRoleException() {
        super("Specified role number is not currently available");
    }
}
