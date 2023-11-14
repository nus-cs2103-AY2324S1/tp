package seedu.address.model.customer.exceptions;

/**
 * Signals that the operation is unable to find the specified customer.
 */
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super("Unable to find specified customer");
    }
}
