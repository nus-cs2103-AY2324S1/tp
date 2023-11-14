package connectify.model.company.exceptions;

/**
 * Represents an error where the operation will result in a duplicate Company.
 * Companies are considered duplicates if they have the same identity.
 */
public class DuplicateCompanyException extends RuntimeException {
    /**
     * Constructs a new {@code DuplicateCompanyException} with the default error message.
     */
    public DuplicateCompanyException() {
        super("Operation would result in duplicate companies");
    }
}
