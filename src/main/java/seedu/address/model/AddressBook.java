package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueAppointmentList appointments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        appointments = new UniqueAppointmentList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons and Appointments in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the person list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setAppointments(newData.getAppointmentList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a person with the same name as {@code name} exists in the address book.
     */
    public boolean hasPerson(Name name) {
        requireNonNull(name);
        return persons.contains(name);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        appointments.setAppointments(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        appointments.remove(key);
    }

    //// appointment-level operations

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the address book.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Adds an appointment to the address book.
     * The appointment must not already exist in the address book.
     */
    public void addAppointment(Appointment a) {
        appointments.add(a);
    }

    /**
     * Replaces the given appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the address book.
     * The appointment identity of {@code editedAppointment} must not be the same as
     * another existing appointment in the address book.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);

        appointments.setAppointment(target, editedAppointment);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeAppointment(Appointment key) {
        appointments.remove(key);
    }

    /**
     * Sorts appointment list.
     */
    public void sortAppointmentList(boolean isAscending, String attribute) {
        appointments.sort(isAscending, attribute);
    }

    /**
     * Sorts patient list.
     */
    public void sortPatientList(boolean isAscending, String attribute) {
        persons.sort(isAscending, attribute);
    }


    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("appointments", appointments)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
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
                && appointments.equals(otherAddressBook.appointments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, appointments);
    }
}
