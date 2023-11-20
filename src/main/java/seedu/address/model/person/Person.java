package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Type type;

    // Data fields
    private final Address address;
    private final Optional<MeetingTime> meetingTime;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Type type,
                  Address address, Optional<MeetingTime> meetingTime, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, meetingTime, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.address = address;
        this.meetingTime = meetingTime;
        this.tags.addAll(tags);
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

    public Type getType() {
        return type;
    }

    public Address getAddress() {
        return address;
    }

    public Optional<MeetingTime> getMeetingTime() {
        return meetingTime;
    }
    public String getMeetingTimeString() {
        return meetingTime.map(MeetingTime::toString).orElse(null);
    }

    public String getMeetingTimeStringForUserInterface() {
        return meetingTime.map(meetingTime -> "Meeting on: " + meetingTime)
                .orElse(null);
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
                && type.equals(otherPerson.type)
                && address.equals(otherPerson.address)
                && meetingTime.equals(otherPerson.meetingTime)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, type, address, meetingTime, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("type", type)
                .add("address", address)
                .add("meetingTime", meetingTime)
                .add("tags", tags)
                .toString();
    }

    public abstract boolean isClient();

    public abstract boolean isLead();
}
