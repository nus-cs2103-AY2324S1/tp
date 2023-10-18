package seedu.address.model.band.exceptions;

/**
 * Signals that the operation will result in duplicate Bands (Bands are considered duplicates if they have the same
 * identity).
 */
public class DuplicateBandException extends RuntimeException {
    public DuplicateBandException() {
        super("Operation would result in duplicate bands");
    }

}
