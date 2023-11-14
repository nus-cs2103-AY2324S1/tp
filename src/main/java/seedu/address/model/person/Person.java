package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Id id;
    private final Phone phone;
    private final Email email;


    // Data fields
    private final Address address;
    private final Appointment appointment;
    private final Set<MedicalHistory> medicalHistories = new HashSet<>();

    /**
     * Every field must be present and not null.
     */

    public Person(Name name, Id id, Phone phone, Email email, Address address, Appointment appointment,
                  Set<MedicalHistory> medicalHistories) {
        requireAllNonNull(name, id, phone, email, address, medicalHistories);
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.appointment = appointment;
        this.medicalHistories.addAll(medicalHistories);
    }


    public Name getName() {
        return name;
    }

    public Id getId() {
        return id;
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

    public Optional<Appointment> getAppointment() {
        return Optional.ofNullable(appointment);
    }
    /**
     * Returns an immutable medical history set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<MedicalHistory> getMedicalHistories() {
        return Collections.unmodifiableSet(medicalHistories);
    }


    /**
     * Returns true if both persons have the same name or ID.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && (otherPerson.getName().equals(getName())
                || otherPerson.getId().equals(getId()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
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
                && id.equals(otherPerson.id)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && Objects.equals(appointment, otherPerson.appointment)
                && medicalHistories.equals(otherPerson.medicalHistories);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, id, phone, email, address, appointment, medicalHistories);

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("id", id)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("appointment", appointment)
                .add("medicalHistories", medicalHistories)
                .toString();
    }
}
