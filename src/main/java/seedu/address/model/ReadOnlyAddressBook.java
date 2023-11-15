package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {
    /**
     * Returns an unmodifiable view of the doctors list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Doctor> getDoctorList();
    ObservableList<Patient> getPatientList();
    ObservableList<Appointment> getAppointmentList();
}
