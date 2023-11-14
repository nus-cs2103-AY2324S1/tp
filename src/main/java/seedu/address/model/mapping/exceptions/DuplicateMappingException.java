package seedu.address.model.mapping.exceptions;

/**
 * Signals that the operation will result in duplicate Mappings (Mapping are considered duplicates if they have
 * the same identity as checked by Mapping#isSameMapping).
 */
public class DuplicateMappingException extends RuntimeException {
    public DuplicateMappingException() {
        super("Operation would result in duplicate mappings");
    }
}
