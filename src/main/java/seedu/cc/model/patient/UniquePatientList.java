package seedu.cc.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.appointment.Prescription;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.exceptions.DuplicatePatientException;
import seedu.cc.model.patient.exceptions.PatientNotFoundException;

/**
 * A list of patients that enforces uniqueness between its elements and does not allow nulls.
 * A patient is considered unique by comparing using {@code Patients#isSamePerson(Patient)}.
 * As such, adding and updating of patients uses Patient#isSamePerson(Patient) for equality so as to ensure
 * that the patient being added or updated is unique in terms of identity in the UniquePatientList. However,
 * the removal of a patient uses Patient#equals(Object) so as to ensure that the patient with exactly the same
 * fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Patient#isSamePatient(Patient)
 */
public class UniquePatientList implements Iterable<Patient> {

    private final ObservableList<Patient> internalList = FXCollections.observableArrayList();
    private final ObservableList<Patient> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Patient toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Patient toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePatientException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the given patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the list.
     * The patient identity of {@code editedPatient} must not be the same as another existing patient in the list.
     */
    public void setPatient(Patient target, Patient editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PatientNotFoundException();
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePatientException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Patient toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PatientNotFoundException();
        }
    }

    public void setPatients(UniquePatientList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        requireAllNonNull(patients);
        if (!patientsAreUnique(patients)) {
            throw new DuplicatePatientException();
        }

        internalList.setAll(patients);
    }

    //=========== Medical History Operations =============================================================

    /**
     * Adds a medical history event to the specified patient.
     * @param patient
     * @param medicalHistoryEvent
     */
    public void addMedicalHistoryEvent(Patient patient, MedicalHistoryEvent medicalHistoryEvent) {
        requireAllNonNull(patient, medicalHistoryEvent);
        int index = internalList.indexOf(patient);
        if (index == -1) {
            throw new PatientNotFoundException();
        }
        patient.addMedicalHistoryEvent(medicalHistoryEvent);
        internalList.set(index, patient);
    }

    /**
     * Sets the MedicalHistoryEvent at the specified index to the edited MedicalHistoryEvent.
     * @param patient
     * @param medicalHistoryEventToEdit
     * @param editedMedicalHistoryEvent
     */
    public void setMedicalHistoryEvent(Patient patient, MedicalHistoryEvent medicalHistoryEventToEdit,
                                       MedicalHistoryEvent editedMedicalHistoryEvent) {
        requireAllNonNull(patient, medicalHistoryEventToEdit, editedMedicalHistoryEvent);
        int index = internalList.indexOf(patient);
        if (index == -1) {
            throw new PatientNotFoundException();
        }

        patient.setMedicalHistoryEvent(medicalHistoryEventToEdit, editedMedicalHistoryEvent);
        internalList.set(index, patient);
    }

    /**
     * Lists all medical history events associated with a specific patient.
     *
     * @param patient The patient for whom to list the medical history events.
     */
    public void deleteMedicalHistoryEvent(Patient patient, MedicalHistoryEvent medicalHistoryEventToDelete) {
        requireAllNonNull(patient, medicalHistoryEventToDelete);
        int index = internalList.indexOf(patient);
        if (index == -1) {
            throw new PatientNotFoundException();
        }

        patient.deleteMedicalHistoryEvent(medicalHistoryEventToDelete);
        internalList.set(index, patient);
    }

    //=============AppointmentEvent Operations=============================================================
    /**
     * Adds an appointmentEvent to the specified patient.
     * @param patient
     * @param appointmentEvent
     */
    public void addAppointment(Patient patient, AppointmentEvent appointmentEvent) {
        requireAllNonNull(patient, appointmentEvent);
        int index = internalList.indexOf(patient);
        if (index == -1) {
            throw new PatientNotFoundException();
        }
        patient.addAppointmentEvent(appointmentEvent);
        internalList.set(index, patient);
    }

    /**
     * Sets the AppointmentEvent at the specified index to the edited AppointmentEvent.
     * @param patient
     * @param appointmentEventToEdit
     * @param editedAppointmentEvent
     */
    public void setAppointment(Patient patient, AppointmentEvent appointmentEventToEdit,
                               AppointmentEvent editedAppointmentEvent) {
        requireAllNonNull(patient, appointmentEventToEdit, editedAppointmentEvent);
        int index = internalList.indexOf(patient);
        if (index == -1) {
            throw new PatientNotFoundException();
        }
        patient.setAppointmentEvent(appointmentEventToEdit, editedAppointmentEvent);
        internalList.set(index, patient);
    }

    /**
     * Deletes the specified AppointmentEvent from the Appointments.
     * @param patient
     * @param appointmentEventToDelete
     */
    public void deleteAppointment(Patient patient, AppointmentEvent appointmentEventToDelete) {
        requireAllNonNull(patient, appointmentEventToDelete);
        int index = internalList.indexOf(patient);
        if (index == -1) {
            throw new PatientNotFoundException();
        }
        patient.deleteAppointmentEvent(appointmentEventToDelete);
        internalList.set(index, patient);
    }

    //=========== Prescription Operations =============================================================

    /**
     * Adds a set of prescriptions to the specified appointment event.
     * @param patient
     * @param appointmentEvent
     * @param prescriptions
     */
    public void addPrescriptions(Patient patient, AppointmentEvent appointmentEvent, Set<Prescription> prescriptions) {
        requireAllNonNull(patient, appointmentEvent, prescriptions);
        int index = internalList.indexOf(patient);
        if (index == -1) {
            throw new PatientNotFoundException();
        }
        patient.addPrescriptions(appointmentEvent, prescriptions);
        internalList.set(index, patient);
    }

    //================================================================================================

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Patient> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Patient> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniquePatientList)) {
            return false;
        }

        UniquePatientList otherUniquePersonList = (UniquePatientList) other;
        return internalList.equals(otherUniquePersonList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean patientsAreUnique(List<Patient> patients) {
        for (int i = 0; i < patients.size() - 1; i++) {
            for (int j = i + 1; j < patients.size(); j++) {
                if (patients.get(i).isSamePerson(patients.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


}
