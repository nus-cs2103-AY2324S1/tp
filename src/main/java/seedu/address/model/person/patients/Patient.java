package seedu.address.model.person.patients;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.treatment.TreatmentName;

/**
 * Represents a Patient in the address book. Guarantees: details are present and not null, field
 * values are validated, immutable.
 */

public class Patient extends Person {

    private final Gender gender;
    private final Birthdate birthdate;
    private final Remark remark;

    private final TreatmentName treatmentName;

    private long id;

    /**
     * A class which represents a patient
     *
     * @param name        name of patient
     * @param phone       phone number of patient
     * @param birthdate   patients birthday
     * @param gender      patients gender
     * @param remark      general remark for the patient
     * @param treatmentName    dental treatment that the patients needs
     * @param address     address of patient
     * @param email       email of patient
     * @param tags        tags
     */
    public Patient(Name name, Phone phone, Birthdate birthdate, Gender gender,
        Remark remark, TreatmentName treatmentName, Address address, Email email, Set<Tag> tags
    ) {
        super(name, phone, email, address, tags);
        requireAllNonNull(name, phone, birthdate, gender, remark, treatmentName, address, email,
            tags);

        this.gender = gender;
        this.birthdate = birthdate;
        this.remark = remark;
        this.treatmentName = treatmentName;
        this.id = -1;

    }

    /**
     * A class which represents a patient
     *
     * @param name        name of patient
     * @param phone       phone number of patient
     * @param birthdate   patients birthday
     * @param gender      patients gender
     * @param remark      general remark for the patient
     * @param treatmentName     dental treatment that the patients needs
     * @param address     address of patient
     * @param email       email of patient
     * @param id          id of patient
     * @param tags        tags
     */
    public Patient(Name name, Phone phone, Birthdate birthdate, Gender gender,
        Remark remark, TreatmentName treatmentName, Address address, Email email, long id,
        Set<Tag> tags
    ) {
        super(name, phone, email, address, tags);
        requireAllNonNull(name, phone, birthdate, gender, remark, treatmentName, address, email,
            tags, id);

        this.gender = gender;
        this.birthdate = birthdate;
        this.remark = remark;
        this.treatmentName = treatmentName;
        this.id = id;

    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public Birthdate getBirthdate() {
        return birthdate;
    }

    public Remark getRemark() {
        return remark;
    }

    public TreatmentName getTreatmentName() {
        return treatmentName;
    }


    /**
     * Returns true if both patients have the same name. This defines a weaker notion of equality
     * between two persons.
     */
    public boolean isSamePatient(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        return otherPatient != null
            && otherPatient.getName().equals(getName());
    }

    /**
     * Returns true if both patients have the same identity and data fields. This defines a stronger
     * notion of equality between two patients.
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

        Patient otherPerson = (Patient) other;
        return super.getName().equals(otherPerson.getName())
            && super.getPhone().equals(otherPerson.getPhone())
            && super.getEmail().equals(otherPerson.getEmail())
            && super.getAddress().equals(otherPerson.getAddress())
            && super.getTags().equals(otherPerson.getTags())
            && getGender().equals(otherPerson.getGender())
            && getBirthdate().equals(otherPerson.getBirthdate())
            && getRemark().equals(otherPerson.getRemark())
            && getTreatmentName().equals(otherPerson.getTreatmentName())
            && id == otherPerson.getId();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(super.getName(), super.getPhone(), super.getEmail(), super.getAddress(),
            super.getTags(), getGender(), getBirthdate(), getRemark(), getTreatmentName(), id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("name", super.getName())
            .add("phone", super.getPhone())
            .add("birthday", getBirthdate())
            .add("gender", getGender())
            .add("remark", getRemark())
            .add("treatment", getTreatmentName())
            .add("address", super.getAddress())
            .add("email", super.getEmail())
            .add("id", id)
            .add("tags", super.getTags())
            .toString();
    }


}

