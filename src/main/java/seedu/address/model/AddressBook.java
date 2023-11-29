package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointments.Appointment;
import seedu.address.model.appointments.UniqueAppointmentList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePatientList;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.dentist.UniqueDentistList;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.treatment.Treatment;
import seedu.address.model.treatment.TreatmentName;
import seedu.address.model.treatment.UniqueTreatmentList;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by .isSamePerson
 * comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniquePatientList patients;
    private final UniqueDentistList dentists;
    private final UniqueAppointmentList appointments;
    private final UniqueTreatmentList treatments;

    private long patientId;
    private long dentistId;
    private long appointmentId;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */

    {
        persons = new UniquePersonList();
        patients = new UniquePatientList();
        dentists = new UniqueDentistList();
        appointments = new UniqueAppointmentList();
        treatments = new UniqueTreatmentList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }


    // ID operations
    public void setPatientId(long id) {
        patientId = id;
    }

    @Override
    public long getPatientId() {
        return patientId;
    }

    public void incrementPatientId() {
        patientId = patientId + 1;
    }

    public void setDentistId(long id) {
        dentistId = id;
    }

    @Override
    public long getDentistId() {
        return dentistId;
    }

    public void incrementDentistId() {
        dentistId = dentistId + 1;
    }

    public void setAppointmentId(long id) {
        appointmentId = id;
    }

    @Override
    public long getAppointmentId() {
        return appointmentId;
    }

    public void incrementAppointmentId() {
        appointmentId = appointmentId + 1;
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}. {@code persons} must not
     * contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the patients list with {@code patients}. {@code patients} must not
     * contain duplicate persons.
     */
    public void setPatients(List<Patient> patients) {
        this.patients.setPatients(patients);
    }

    /**
     * Replaces the contents of the dentist list with {@code dentists}. {@code dentists} must not
     * contain duplicate dentists.
     */
    public void setDentists(List<Dentist> dentists) {
        this.dentists.setDentists(dentists);
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Replaces the contents of the Treatments list with {@code treatments}. {@code treatments} must
     * not contain duplicate treatments.
     */
    public void setTreatments(List<Treatment> treatments) {
        this.treatments.setTreatments(treatments);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setPatients(newData.getPatientList());
        setDentists(newData.getDentistList());
        setTreatments(newData.getTreatmentList());
        setAppointments(newData.getAppointmentList());
        setPatientId(newData.getPatientId());
        setDentistId(newData.getDentistId());
        setAppointmentId(newData.getAppointmentId());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address
     * book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address
     * book.
     */
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patients.contains(patient);
    }

    /**
     * Returns true if a treatment with the same fields as {@code treatment} exists in the address
     * book.
     */
    public boolean hasTreatment(Treatment treatment) {
        requireNonNull(treatment);
        return treatments.contains(treatment);
    }

    /**
     * Returns true if a treatmentName with the same fields as {@code treatmentName} exists in the
     * address book.
     */
    public boolean hasTreatmentName(TreatmentName treatmentName) {
        requireNonNull(treatmentName);
        return treatments.contains(treatmentName);
    }

    /**
     * Adds a person to the address book. The person must not already exist in the address book.
     * Returns true if a dentist with the same identity as {@code dentist} exists in the address
     * book.
     */
    public boolean hasDentist(Dentist dentist) {
        requireNonNull(dentist);
        return dentists.contains(dentist);
    }

    /**
     * Returns true if the internal list of appointments contains the specified appointment.
     *
     * @param appointment The appointment to check for existence.
     * @return True if the appointment is found in the list, false otherwise.
     * @throws NullPointerException if the given appointment is null.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Adds a person to the address book. The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a treatment to the address book. The treatment must not already exist in the address
     * book.
     */
    public void addTreatment(Treatment p) {
        treatments.add(p);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in the address
     * book. Replaces the given treatment {@code target} in the list with {@code editedtreatment}.
     * {@code target} must exist in the address book. The person identity of {@code editedtreatment}
     * must not be the same as another existing treatment in the address book.
     */
    public void setTreatment(Treatment target, Treatment editedtreatment) {
        requireNonNull(editedtreatment);

        treatments.setTreatment(target, editedtreatment);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in the address
     * book.
     */
    public void removeTreatment(Treatment key) {
        treatments.remove(key);
    }


    /**
     * Adds a patient to the address book. The patient must not already exist in the address book.
     */
    public void addPatient(Patient p) {
        if (p.getId() == -1) {
            p.setId(patientId);
            patients.add(p);
            incrementPatientId();
        } else {
            patients.add(p);
        }
    }

    /**
     * Adds a dentist to the address book. The dentist must not already exist in the address book.
     *
     * @param dentist to be added to the address book
     */
    public void addDentist(Dentist dentist) {
        if (dentist.getId() == -1) {
            dentist.setId(dentistId);
            dentists.add(dentist);
            incrementDentistId();
        } else {
            dentists.add(dentist);
        }
    }

    /**
     * Adds an appointment to the list of appointments. If the appointment has an ID of -1, it
     * assigns a new ID and increments the global appointment ID counter.
     *
     * @param appointment The appointment to be added.
     */
    public void addAppointment(Appointment appointment) {
        if (appointment.getId() == -1) {
            appointment.setId(appointmentId);
            appointments.add(appointment);
            incrementAppointmentId();
        } else {
            appointments.add(appointment);
        }
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book. The person identity of {@code editedPerson}
     * must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book. The person identity of {@code editedPerson}
     * must not be the same as another existing person in the address book.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);

        patients.setPatient(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in the address
     * book. Replaces the given dentist {@code target} in the list with {@code editedDentist}.
     * {@code target} must exist in the address book. The person identity of {@code editedDentist}
     * must not be the same as another existing person in the address book.
     */
    public void setDentist(Dentist target, Dentist editedDentist) {
        requireNonNull(editedDentist);

        dentists.setDentist(target, editedDentist);
    }

    public Dentist getDentist(int targetIndex) {
        requireNonNull(targetIndex);

        return dentists.getDentist(targetIndex);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in the address
     * book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in the address
     * book.
     */
    public void removePatient(Patient key) {
        patients.remove(key);
    }

    public void removeDentist(Dentist key) {
        dentists.remove(key);
    }

    public void removeAppointment(Appointment key) {
        appointments.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("persons", persons)
            .add("patients", patients)
            .add("dentists", dentists)
            .add("appointments", appointments)
            .add("treatments", treatments)
            .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return patients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Treatment> getTreatmentList() {
        return treatments.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Dentist> getDentistList() {
        return dentists.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons)
            && dentists.equals(otherAddressBook.dentists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patients, dentists, appointments, treatments);
    }

}
