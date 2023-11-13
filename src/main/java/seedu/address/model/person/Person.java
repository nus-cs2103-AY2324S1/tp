package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
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
    private final LastContactedTime lastContactedTime;

    // Data fields
    private final Remark remark;
    private final Status status;
    private final Set<Tag> tags;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, LocalDateTime time, Status status, Remark remark,
            Set<Tag> tags) {
        requireAllNonNull(name, phone, email, time, status, remark, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.lastContactedTime = new LastContactedTime(time);
        this.status = status;
        this.remark = remark;
        this.tags = new HashSet<>(tags);
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

    public LocalDateTime getLastContactedTime() {
        return lastContactedTime.getTime();
    }

    public String getLastContactedDisplay() {
        return lastContactedTime.getDisplay();
    }

    public Remark getRemark() {
        return remark;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same email, name or phone.
     */
    public boolean isDuplicate(Person otherPerson) {
        if (otherPerson == null) {
            return false;
        }

        if (otherPerson.getName().equals(getName())) {
            return true;
        }

        if (otherPerson.getPhone().equals(getPhone())) {
            return true;
        }

        if (otherPerson.getEmail().equals(getEmail())) {
            return true;
        }

        return false;
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
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && lastContactedTime.equals(otherPerson.lastContactedTime)
                && status.equals(otherPerson.status)
                && remark.equals(otherPerson.remark)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, lastContactedTime, status, remark, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("last contacted time", lastContactedTime.getTime())
                .add("status", status)
                .add("remark", remark)
                .add("tags", tags)
                .toString();
    }

    /**
     * Returns detailed information of Person for viewc command.
     */
    public String toDisplayString() {
        return String.format("Name: %s\nPhone: %s\nEmail: %s\nLast Meeting: %s\nStatus: %s\nRemark: %s",
                name, phone, email, lastContactedTime.getDisplay(), status, remark);
    }
}
