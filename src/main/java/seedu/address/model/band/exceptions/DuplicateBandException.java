package seedu.address.model.band.exceptions;

public class DuplicateBandException extends RuntimeException {
    public DuplicateBandException() {
        super("Operation would result in duplicate bands");
    }

}
