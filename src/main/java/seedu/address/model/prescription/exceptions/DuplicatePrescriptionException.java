package seedu.address.model.prescription.exceptions;

/**
 * Signals that the operation will result in duplicate Prescriptions (Prescriptions
 * are considered duplicates if they have the same identity).
 */
public class DuplicatePrescriptionException extends RuntimeException {
    public DuplicatePrescriptionException() {
        super("Operation would result in duplicate prescriptions");
    }
}
