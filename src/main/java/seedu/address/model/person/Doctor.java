package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Doctor in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor extends Person {
    private ArrayList<Patient> patients = new ArrayList<Patient>();

    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email, Address address, Remark remark, Gender gender,
                  Ic ic, Set<Tag> tags) {
        super(name, phone, email, address, remark, gender, ic, tags);
    }
    /**
     * Retrieves the list of patients stored in this medical facility.
     *
     * @return An ArrayList containing the patients currently registered in the facility.
     */
    public ArrayList<Patient> getPatients() {
        return patients;
    }

    /**
     * Adds a new patient to the medical facility's list of patients.
     *
     * @param patient The Patient object representing the individual to be added.
     */
    public void addPatient(Patient patient) {
        this.patients.add(patient);
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

        Doctor otherDoctor = (Doctor) other;
        return name.equals(otherDoctor.name)
                && phone.equals(otherDoctor.phone)
                && email.equals(otherDoctor.email)
                && address.equals(otherDoctor.address)
                && gender.equals(otherDoctor.gender)
                && ic.equals(otherDoctor.ic)
                && tags.equals(otherDoctor.tags)
                && patients.equals(otherDoctor.patients);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, gender, ic, tags, patients);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("remark", remark)
                .add("gender", gender)
                .add("nric", ic)
                .add("patients", patients)
                .add("tags", tags)
                .toString();
    }

}

