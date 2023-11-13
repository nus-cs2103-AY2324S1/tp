package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Nric nric;
    private final Gender gender;
    private final Age age;
    private final Ethnicity ethnic;
    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Gender gender,
                  Age age, Ethnicity ethnic, Nric nric, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, gender, age, ethnic, nric, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.ethnic = ethnic;
        this.nric = nric;
        this.address = address;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Gender gender, Age age, Ethnicity ethnic, Nric nric,
                  Address address, Set<Tag> tags, ArrayList<Appointment> appointments) {
        requireAllNonNull(name, phone, email, gender, age, ethnic, nric, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.ethnic = ethnic;
        this.nric = nric;
        this.address = address;
        this.tags.addAll(tags);
        this.appointments.addAll(appointments);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Nric getNric() {
        return nric;
    }

    public Gender getGender() {
        return gender;
    }
    public Age getAge() {
        return age;
    }
    public Ethnicity getEthnic() {
        return ethnic;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * returns First Appointment of the Patient
     *
     * @return First Appointment of patient
     */
    public Appointment firstAppointment() {
        return appointments.get(0);
    }

    /**
     * Adds an appointment to this Person
     * @param toAdd Appointment to be added
     */
    public void addAppointment(Appointment toAdd) {
        this.appointments.add(toAdd);
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * @param index Index of the appointment to delete
     * @return An ArrayList without the deleted appointment
     */
    public Appointment deleteAppointment(int index) {
        Appointment appt = appointments.get(index);
        this.appointments.remove(index);
        return appt;
    }

    /**
     * Edits the appointment at the input index with the updated Appointment
     * @param index Index of the appointment to edit
     * @param updatedAppointment the updated Appointment
     */
    public void editAppointment(int index, Appointment updatedAppointment) {
        this.appointments.set(index, updatedAppointment);
    }

    /**
     * Returns true if both persons have the same name or same nric.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == null) {
            return false;
        }
        if (otherPerson == this) {
            return true;
        }
        return (otherPerson.getNric().equals(getNric()));
    }

    /**
     * Returns true if both patients have the same Appointments.
     * @param otherPerson the other patient to check
     * @return if both patients have the same appointment
     */
    public boolean haveSameAppointments(Person otherPerson) {
        return this.appointments.equals(otherPerson.getAppointments());
    }
    /**
     * Checks if a patient already has an appointment on the same timeslot as the given appointment.
     *
     * @param otherAppointment The appointment to compare against existing appointments.
     * @return true if the patient has an appointment with the same timeslot; false otherwise.
     */
    public boolean hasAppointmentOnTimeslot(Appointment otherAppointment) {
        return this.appointments.stream()
                .anyMatch(appointment -> appointment.getDateTime().equals(otherAppointment.getDateTime()));
    }

    /**
     * Returns true if patient already has this same Appointment
     * @param otherAppointment Appointment to check if it exists
     * @return if patient has this Appointment
     */
    public boolean hasAppointment(Appointment otherAppointment) {
        return this.appointments.contains(otherAppointment);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToClone}
     * Needed for edit Appointment.
     */
    public static Person createClone(Person personToClone) {
        assert personToClone != null;

        Name cloneName = personToClone.getName();
        Phone clonePhone = personToClone.getPhone();
        Email cloneEmail = personToClone.getEmail();
        Gender cloneGender = personToClone.getGender();
        Age cloneAge = personToClone.getAge();
        Ethnicity cloneEthnicity = personToClone.getEthnic();
        Nric cloneNric = personToClone.getNric();
        Address cloneAddress = personToClone.getAddress();
        Set<Tag> cloneTags = new HashSet<>(personToClone.getTags());
        ArrayList<Appointment> cloneAppointments = new ArrayList<>(personToClone.getAppointments());

        return new Person(cloneName, clonePhone, cloneEmail, cloneGender, cloneAge, cloneEthnicity, cloneNric,
                cloneAddress, cloneTags, cloneAppointments);
    }

    /**
     * Returns true if both persons have the same name, phone number and nric fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && nric.equals(otherPerson.nric)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, gender, age, ethnic, nric, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("gender", gender)
                .add("age", age)
                .add("ethnic", ethnic)
                .add("nric", nric)
                .add("address", address)
                .add("tags", tags)
                .add("appointments", appointments)
                .toString();
    }
}
