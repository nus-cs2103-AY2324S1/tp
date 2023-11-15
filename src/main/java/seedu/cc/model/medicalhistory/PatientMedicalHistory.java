package seedu.cc.model.medicalhistory;

import java.util.ArrayList;

/**
 * Represents a patient's medical history.
 */
public class PatientMedicalHistory {

    private final ArrayList<MedicalHistoryEvent> medicalHistoryEvents;

    public PatientMedicalHistory() {
        this.medicalHistoryEvents = new ArrayList<>();
    }

    /**
     * Creates a PatientMedicalHistory using the MedicalHistoryEvents in the {@code toBeCopied}
     */
    public void addMedicalHistoryEvent(MedicalHistoryEvent event) {
        medicalHistoryEvents.add(event);

    }

    /**
     * Returns an ArrayList of MedicalHistoryEvents.
     * @return ArrayList of MedicalHistoryEvents.
     */
    public ArrayList<MedicalHistoryEvent> getMedicalHistoryEvents() {
        return medicalHistoryEvents;
    }

    /**
     * Sets the MedicalHistoryEvent at the specified index to the edited MedicalHistoryEvent.
     */
    public void setMedicalHistoryEvent(MedicalHistoryEvent eventToEdit, MedicalHistoryEvent editedEvent) {
        int index = medicalHistoryEvents.indexOf(eventToEdit);
        if (index >= 0 && index < medicalHistoryEvents.size()) {
            medicalHistoryEvents.set(index, editedEvent);
        }
    }

    /**
     * Deletes the specified MedicalHistoryEvent from the PatientMedicalHistory.
     */
    public void deleteMedicalHistoryEvent(MedicalHistoryEvent eventToDelete) {
        int index = medicalHistoryEvents.indexOf(eventToDelete);
        if (index >= 0 && index < medicalHistoryEvents.size()) {
            medicalHistoryEvents.remove(index);
        }
    }

    /**
     * Returns true if the PatientMedicalHistory contains the specified MedicalHistoryEvent.
     */
    public boolean hasMedicalHistoryEvent(MedicalHistoryEvent event) {
        return medicalHistoryEvents.contains(event);
    }


}
