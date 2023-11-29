package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointments.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.treatment.Treatment;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the patients list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Patient> getPatientList();

    /**
     * Returns an unmodifiable view of the dentists list.
     * This list will not contain any duplicate dentists.
     */

    ObservableList<Dentist> getDentistList();

    ObservableList<Appointment> getAppointmentList();

    /**
     * Returns an unmodifiable view of the treatments list.
     * This list will not contain any duplicate dentists.
     */
    ObservableList<Treatment> getTreatmentList();

    /**
     * Returns an integer which represents the next available patient id
     */
    long getPatientId();

    /**
     * Returns an integer which represents the next available dentist id
     */
    long getDentistId();

    /**
     * Returns an integer which represents the next available appointment id
     */
    long getAppointmentId();

}
