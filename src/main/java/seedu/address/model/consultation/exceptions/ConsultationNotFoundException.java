package seedu.address.model.consultation.exceptions;

/**
 * Signals that the operation is unable to find the specified consultation.
 */
public class ConsultationNotFoundException extends RuntimeException {
    public ConsultationNotFoundException() {
        super("Consultation not found");
    }
}
