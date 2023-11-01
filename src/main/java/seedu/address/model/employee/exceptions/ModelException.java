package seedu.address.model.employee.exceptions;

/**
 * Represents failure to comply with model constraints.
 */
public class ModelException extends RuntimeException {
    public ModelException(String error) {
        super(error);
    }
}
