package seedu.address.model.employee.exceptions;

/**
 * Signals that the operation will result in employees with superiors not present in list.
 */
public class SubordinatePresentException extends ModelException {
    public SubordinatePresentException() {
        super("This employee is still in charge of other employees");
    }
}
