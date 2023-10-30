package seedu.address.model.employee.exceptions;

public class SubordinatePresentException extends ModelException {
    public SubordinatePresentException() {
        super("This employee is still in charge of other employees");
    }
}
