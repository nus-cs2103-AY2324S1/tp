package seedu.address.model.company.exceptions;

/**
 * Signals that the operation will result in duplicate Companies (Companies are considered duplicates if they have
 * the same identity).
 * This is only to be used for model's testing. It is not to be used for user interaction.
 */
public class DuplicateCompanyException extends RuntimeException {
    public DuplicateCompanyException() {
        super("Operation would result in duplicate companies");
    }
}
