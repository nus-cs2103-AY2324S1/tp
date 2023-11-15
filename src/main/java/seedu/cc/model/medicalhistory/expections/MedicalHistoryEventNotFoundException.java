package seedu.cc.model.medicalhistory.expections;

/**
 * Signals that the operation is unable to find the specified medical history event.
 */
public class MedicalHistoryEventNotFoundException extends RuntimeException {
    public MedicalHistoryEventNotFoundException() {
        super("Medical history not found");
    }
}

