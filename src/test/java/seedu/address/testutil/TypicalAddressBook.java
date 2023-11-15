package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;

/**
 * A utility class that will combine Typical Doctors and Typical Patients
 * to be used for testing.
 */
public class TypicalAddressBook {

    private TypicalAddressBook() {
    } // prevents instantiation

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Patient patient : TypicalPatient.getTypicalPatients()) {
            ab.addPatient(patient);
        }
        for (Doctor doctor : TypicalDoctor.getTypicalDoctors()) {
            ab.addDoctor(doctor);
        }

        for (Appointment appointment : TypicalAppointment.getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }
}
