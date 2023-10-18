package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.UniqueDoctorList;
import seedu.address.model.person.UniquePatientList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {
    private final UniqueDoctorList doctors;
    private final UniquePatientList patients;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        doctors = new UniqueDoctorList();
        patients = new UniquePatientList();
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

    //// list overwrite operations

    /**
     * Replaces the contents of the patient list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        this.patients.setPatients(patients);
    }

    /**
     * Replaces the contents of the doctors list with {@code doctors}.
     * {@code doctors} must not contain duplicate persons.
     */
    public void setDoctors(List<Doctor> doctors) {
        this.doctors.setDoctors(doctors);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setDoctors(newData.getDoctorList());
        setPatients(newData.getPatientList());
    }

    //// person-level operations

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     */
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        // return in patients list or in doctor list
        return patients.contains(patient);
    }

    /**
     * Returns true if a doctor with the same identity as {@code doctor} exists in the address book.
     */
    public boolean hasDoctor(Doctor doctor) {
        requireNonNull(doctor);
        return doctors.contains(doctor);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     * Adds a patient to the address book.
     * The patient must not already exist in the address book.
     */
    public void addPatient(Patient p) {
        patients.add(p);
    }

    /**
     * Adds a doctor to the address book.
     * The doctor must not already exist in the address book.
     */
    public void addDoctor(Doctor d) {
        doctors.add(d);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * Replaces the given patients {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPatient(Patient target, Patient editedPerson) {
        requireNonNull(editedPerson);

        patients.setPatient(target, editedPerson);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setDoctor(Doctor target, Doctor editedDoctor) {
        requireNonNull(editedDoctor);

        doctors.setDoctor(target, editedDoctor);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePatient(Patient key) {
        if (patients.contains(key)) {
            patients.remove(key);
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeDoctor(Doctor key) {
        if (doctors.contains(key)) {
            doctors.remove(key);
        }
    }

    //// util methods

    @Override
    public String toString() { //not sure how to modify this
        return new ToStringBuilder(this)
                .add("patients", patients)
                .toString();
    }

    public ObservableList<Patient> getPatientList() {
        return patients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Doctor> getDoctorList() {
        return doctors.asUnmodifiableObservableList();
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
        return patients.equals(otherAddressBook.patients)
                && doctors.equals((otherAddressBook.doctors));
    }

    @Override
    public int hashCode() {
        return patients.hashCode();
    }
}
