package seedu.address.model.consultation.exceptions;

/**
 * Signals that the operation will result in duplicate Consultations.
 */
public class DuplicateConsultationException extends RuntimeException {
    public DuplicateConsultationException() {
        super("Operation would result in duplicate consultations");
    }
}
