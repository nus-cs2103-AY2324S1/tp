package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 * Extends abstract class {@code Person}
 */
public class Patient extends Person {

    // Patient specific fields
    private final Condition condition;
    private final BloodType bloodType;
    private final Set<Appointment> appointments = new HashSet<>();
    private final Phone emergencyContact;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Phone emergencyContact, Email email, Address address, Remark remark,
                   Gender gender, Ic ic, Condition condition, BloodType bloodType, Set<Appointment> appointments,
                   Set<Tag> tags) {
        super(name, phone, email, address, remark, gender, ic, appointments, tags);
        requireAllNonNull(condition, bloodType, emergencyContact);
        this.condition = condition;
        this.bloodType = bloodType;
        this.emergencyContact = emergencyContact;
    }

    public Condition getCondition() {
        return condition;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public Phone getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * Returns true if person is a {@code Doctor}.
     */
    @Override
    public boolean isDoctor() {
        return false;
    }

    /**
     * Returns true if person is a {@code Patient}.
     */
    @Override
    public boolean isPatient() {
        return true;
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
        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return name.equals(otherPatient.name)
                && phone.equals(otherPatient.phone)
                && emergencyContact.equals(otherPatient.emergencyContact)
                && email.equals(otherPatient.email)
                && address.equals(otherPatient.address)
                && gender.equals(otherPatient.gender)
                && ic.equals(otherPatient.ic)
                && tags.equals(otherPatient.tags)
                && condition.equals(otherPatient.condition)
                && bloodType.equals(otherPatient.bloodType)
                && appointments.equals(otherPatient.appointments);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, emergencyContact, email, address, gender, ic, condition, bloodType,
                appointments, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("emergency contact", emergencyContact)
                .add("email", email)
                .add("address", address)
                .add("remark", remark)
                .add("gender", gender)
                .add("nric", ic)
                .add("condition", condition)
                .add("bloodType", bloodType)
                .add("appointments", appointments)
                .add("tags", tags)
                .toString();
    }
}

