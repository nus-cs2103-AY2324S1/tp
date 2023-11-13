package seedu.address.model.patient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Gender gender;
    private final IcNumber icNumber;
    private final Birthday birthday;
    private final Age age;

    // Data fields
    private final Address address;
    private final Priority priority;
    private final Set<Tag> tags = new HashSet<>();
    private final AssignedDepartment assignedDepartment;
    private final Record record;

    /**
     * Patient constructor which omits assignedDepartment and Record parameters
     *
     * @param name     the name
     * @param phone    the phone
     * @param email    the email
     * @param gender   the gender
     * @param icNumber the ic number
     * @param birthday the birthday
     * @param address  the address
     * @param priority the priority
     * @param tags     the tags
     */
    public Patient(Name name, Phone phone, Email email, Gender gender, IcNumber icNumber, Birthday birthday,
                   Address address, Priority priority, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, icNumber, birthday, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.icNumber = icNumber;
        this.birthday = birthday;
        this.age = new Age(this.birthday);
        this.address = address;
        this.priority = priority;
        this.tags.addAll(tags);
        this.assignedDepartment = new AssignedDepartment(); // default Department given
        this.record = new Record(this); // creates new Record
    }

    /**
     * Patient constructor which omits Record parameter
     *
     * @param name               the name
     * @param phone              the phone
     * @param email              the email
     * @param gender             the gender
     * @param icNumber           the ic number
     * @param birthday           the birthday
     * @param address            the address
     * @param priority           the priority
     * @param tags               the tags
     * @param assignedDepartment the assigned department
     */
    public Patient(Name name, Phone phone, Email email, Gender gender, IcNumber icNumber, Birthday birthday,
                   Address address, Priority priority, Set<Tag> tags, AssignedDepartment assignedDepartment) {
        requireAllNonNull(name, phone, email, icNumber, birthday, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.icNumber = icNumber;
        this.birthday = birthday;
        this.age = new Age(this.birthday);
        this.address = address;
        this.priority = priority;
        this.tags.addAll(tags);
        this.assignedDepartment = assignedDepartment;
        this.record = new Record(this); // creates new Record
    }

    /**
     * Patient constructor with all fields as parameters
     *
     * @param name               the name
     * @param phone              the phone
     * @param email              the email
     * @param gender             the gender
     * @param icNumber           the ic number
     * @param birthday           the birthday
     * @param address            the address
     * @param priority           the priority
     * @param tags               the tags
     * @param assignedDepartment the assigned department
     * @param record             the record
     */
    public Patient(Name name, Phone phone, Email email, Gender gender, IcNumber icNumber, Birthday birthday,
                   Address address, Priority priority, Set<Tag> tags, AssignedDepartment assignedDepartment,
                   Record record) {
        requireAllNonNull(name, phone, email, icNumber, birthday, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.icNumber = icNumber;
        this.birthday = birthday;
        this.age = new Age(this.birthday);
        this.address = address;
        this.priority = priority;
        this.tags.addAll(tags);
        this.assignedDepartment = assignedDepartment;
        this.record = new Record(this);
        this.record.copyRecordInfo(record);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public Name getName() {
        return name;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets ic number.
     *
     * @return the ic number
     */
    public IcNumber getIcNumber() {
        return icNumber;
    }

    /**
     * Gets birthday.
     *
     * @return the birthday
     */
    public Birthday getBirthday() {
        return birthday;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public Age getAge() {
        return age;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Gets assigned department.
     *
     * @return the assigned department
     */
    public AssignedDepartment getAssignedDepartment() {
        return assignedDepartment;
    }

    /**
     * Gets record.
     *
     * @return the record
     */
    public Record getRecord() {
        return record;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return the tags
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both patients have the same name.
     * This defines a weaker notion of equality between two patients.
     *
     * @param otherPatient the other patient
     * @return the boolean
     */
    public boolean isSamePatient(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        return otherPatient != null && otherPatient.getName().equals(getName());
    }

    /**
     * Returns true if both patients have the same identity and data fields.
     * This defines a stronger notion of equality between two patients.
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
        return name.equals(otherPatient.name) && phone.equals(otherPatient.phone) && email.equals(otherPatient.email)
            && gender.equals(otherPatient.gender) && icNumber.equals(otherPatient.icNumber)
            && birthday.equals(otherPatient.birthday) && age.equals(otherPatient.age)
            && address.equals(otherPatient.address) && priority.equals(otherPatient.priority)
            && tags.equals(otherPatient.tags) && assignedDepartment.equals(otherPatient.assignedDepartment)
            && record.equals(otherPatient.record);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, gender, icNumber,
                birthday, age, address, tags, assignedDepartment, record);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("name", name).add("phone", phone).add("email", email).add("gender", gender)
            .add("icNumber", icNumber).add("birthday", birthday).add("age", age)
            .add("address", address).add("priority", priority).add("tags", tags)
            .add("assignedDepartment", assignedDepartment).add("record", record).toString();
    }

}
