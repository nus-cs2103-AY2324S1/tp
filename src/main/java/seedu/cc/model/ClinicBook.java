package seedu.cc.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.cc.commons.util.ToStringBuilder;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.appointment.ClinicBookAppointmentList;
import seedu.cc.model.appointment.Prescription;
import seedu.cc.model.medicalhistory.ClinicBookMedicalHistory;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;
import seedu.cc.model.patient.UniquePatientList;

/**
 * Wraps all data at the clinic-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ClinicBook implements ReadOnlyClinicBook {

    private final UniquePatientList patients;
    private final ClinicBookMedicalHistory clinicBookMedicalHistory;
    private final ClinicBookAppointmentList appointmentsUniqueListClinicBook;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        patients = new UniquePatientList();
        clinicBookMedicalHistory = new ClinicBookMedicalHistory();
        appointmentsUniqueListClinicBook = new ClinicBookAppointmentList();
    }

    public ClinicBook() {}

    /**
     * Creates an ClinicBook using the Persons in the {@code toBeCopied}
     */
    public ClinicBook(ReadOnlyClinicBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the patient list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        this.patients.setPatients(patients);
    }

    /**
     * Resets the existing data of this {@code ClinicBook} with {@code newData}.
     */
    public void resetData(ReadOnlyClinicBook newData) {
        requireNonNull(newData);

        setPatients(newData.getPatientList());
    }

    //// person-level operations

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the clinic book.
     */
    public boolean hasPatient(Patient person) {
        requireNonNull(person);
        return patients.contains(person);
    }

    /**
     * Adds a patient to the clinic book.
     * The patient must not already exist in the clinic book.
     */
    public void addPatient(Patient p) {
        patients.add(p);
    }

    /**
     * Replaces the given patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the clinic book.
     * The patient identity of {@code editedPatient} must not be the same as another
     *  existing patient in the clinic book.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);

        patients.setPatient(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code ClinicBook}.
     * {@code key} must exist in the clinic book.
     */
    public void removePatient(Patient key) {
        patients.remove(key);
    }

    //===============Medical History Operations=========================================================

    /**
     * Adds a medical history event to a patient's medical history. This method updates both the patient's
     * medical history and the list of medical history events.
     *
     * @param patient The patient to whom the medical history event should be added.
     * @param medicalHistoryEvent The medical history event to add to the patient's history.
     */
    public void addMedicalHistoryEvent(Patient patient, MedicalHistoryEvent medicalHistoryEvent) {
        patients.addMedicalHistoryEvent(patient, medicalHistoryEvent);
        clinicBookMedicalHistory.add(medicalHistoryEvent, patient);
    }

    /**
     * Lists all medical history events associated with a specific patient.
     *
     * @param patient The patient for whom to list the medical history events.
     */
    public void listMedicalHistoryEvents(Patient patient) {
        clinicBookMedicalHistory.listMedicalHistoryEvents(patient);
    }

    /**
     * Deletes a specific medical history event from a patient's medical history. This method updates both
     * the patient's medical history and the list of medical history events.
     *
     * @param patient The patient from whose history the event should be deleted.
     * @param medicalHistoryEventToDelete The medical history event to delete.
     */
    public void deleteMedicalHistoryEvent(Patient patient, MedicalHistoryEvent medicalHistoryEventToDelete) {
        patients.deleteMedicalHistoryEvent(patient, medicalHistoryEventToDelete);
        clinicBookMedicalHistory.delete(medicalHistoryEventToDelete, patient);
    }

    /**
     * Edits a medical history event in a patient's medical history. This method updates both the patient's
     * medical history and the list of medical history events.
     *
     * @param patient The patient whose medical history should be edited.
     * @param medicalHistoryEventToEdit The medical history event to be edited.
     * @param editedMedicalHistoryEvent The edited version of the medical history event.
     */
    public void setMedicalHistoryEvent(Patient patient, MedicalHistoryEvent medicalHistoryEventToEdit,
                                       MedicalHistoryEvent editedMedicalHistoryEvent) {
        patients.setMedicalHistoryEvent(patient, medicalHistoryEventToEdit, editedMedicalHistoryEvent);
        clinicBookMedicalHistory.setMedicalHistoryEvent(medicalHistoryEventToEdit, editedMedicalHistoryEvent, patient);
    }

    @Override
    public ObservableList<MedicalHistoryEvent> getClinicBookMedicalHistory() {
        return clinicBookMedicalHistory.asUnmodifiableObservableList();
    }

    //=============AppointmentEvent Operations=============================================================
    /**
     * Adds an appointment event to a patient's appointment list. This method updates both the patient's
     * appointment list and the list of appointment events.
     *
     * @param patient The patient to whom the appointment event should be added.
     * @param appointmentEvent The appointment event to add to the patient's appointment list.
     */
    public void addAppointment(Patient patient, AppointmentEvent appointmentEvent) {
        patients.addAppointment(patient, appointmentEvent);
        appointmentsUniqueListClinicBook.add(appointmentEvent, patient);
    }

    /**
     * Edits an appointment event in a patient's appointment list. This method updates both the patient's
     * appointment list and the list of appointment events.
     *
     * @param patient The patient whose appointment list should be edited.
     * @param appointmentEventToEdit The appointment event to be edited.
     * @param editedAppointmentEvent The edited version of the appointment event.
     */
    public void setAppointment(Patient patient, AppointmentEvent appointmentEventToEdit,
                               AppointmentEvent editedAppointmentEvent) {
        patients.setAppointment(patient, appointmentEventToEdit, editedAppointmentEvent);
        appointmentsUniqueListClinicBook.setAppointment(appointmentEventToEdit, editedAppointmentEvent, patient);
    }

    /**
     * Deletes a specific appointment event from a patient's appointment list. This method updates both
     * the patient's appointment list and the list of appointment events.
     *
     * @param patient The patient from whose appointment list the event should be deleted.
     * @param appointmentEventToDelete The appointment event to delete.
     */
    public void deleteAppointment(Patient patient, AppointmentEvent appointmentEventToDelete) {
        patients.deleteAppointment(patient, appointmentEventToDelete);
        appointmentsUniqueListClinicBook.delete(appointmentEventToDelete, patient);
    }

    /**
     * Lists all appointment events associated with a specific patient.
     *
     * @param patient The patient for whom to list the appointment events.
     */
    public void listAppointments(Patient patient) {
        appointmentsUniqueListClinicBook.listAppointments(patient);
    }

    @Override
    public ObservableList<AppointmentEvent> getClinicBookAppointments() {
        return appointmentsUniqueListClinicBook.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return patients.asUnmodifiableObservableList();
    }

    //=========== Prescription Operations =============================================================
    public void addPrescriptions(Patient patient, AppointmentEvent appointmentEvent, Set<Prescription> prescriptions) {
        patients.addPrescriptions(patient, appointmentEvent, prescriptions);
    }
    //=====================================================================================================

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("patients", patients)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClinicBook)) {
            return false;
        }

        ClinicBook otherClinicBook = (ClinicBook) other;
        return patients.equals(otherClinicBook.patients);
    }

    @Override
    public int hashCode() {
        return patients.hashCode();
    }
}
