package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient extends Person {

    // Patient specific fields
    private final Condition condition;
    private final BloodType bloodType;
    //private final Doctor doctor;    to be implemented after Doctor class created
    private Phone emergencyContact;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Phone emergencyContact, Email email, Address address, Remark remark,
                   Gender gender, Ic ic, Condition condition, BloodType bloodType, Set<Tag> tags) {
        super(name, phone, email, address, remark, gender, ic, tags);
        requireAllNonNull(condition, bloodType);
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

        Patient otherPatient = (Patient) other;
        return name.equals(otherPatient.name)
                && phone.equals(otherPatient.phone)
                && email.equals(otherPatient.email)
                && address.equals(otherPatient.address)
                && gender.equals(otherPatient.gender)
                && ic.equals(otherPatient.ic)
                && tags.equals(otherPatient.tags)
                && condition.equals(otherPatient.condition)
                && bloodType.equals(otherPatient.bloodType);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, gender, ic, condition, bloodType, tags);
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
                .add("tags", tags)
                .toString();
    }

}

