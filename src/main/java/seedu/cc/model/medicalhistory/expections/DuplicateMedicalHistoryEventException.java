package seedu.cc.model.medicalhistory.expections;

/**
 * Signals that the operation will result in duplicate MedicalHistoryEvent
 * (MedicalHistoryEvents are considered duplicates if they have the same
 * identity).
 */
public class DuplicateMedicalHistoryEventException extends RuntimeException {
    public DuplicateMedicalHistoryEventException() {
        super("Operation would result in duplicate medical history");
    }
}

