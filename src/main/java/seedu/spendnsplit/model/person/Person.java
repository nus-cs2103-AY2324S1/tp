package seedu.spendnsplit.model.person;

import static seedu.spendnsplit.commons.util.AppUtil.checkArgument;
import static seedu.spendnsplit.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.spendnsplit.commons.util.ToStringBuilder;
import seedu.spendnsplit.model.tag.Tag;

/**
 * Represents a Person.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity field
    private final Name name;

    // Data fields
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final TelegramHandle telegramHandle;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, TelegramHandle telegramHandle, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name);
        checkArgument(!Name.isReservedName(name), String.format(Name.RESERVED_CONSTRAINTS, name.toString()));
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.telegramHandle = telegramHandle;
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

    public Address getAddress() {
        return address;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
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
                && (phone == otherPerson.phone
                || (phone != null && phone.equals(otherPerson.phone)))
                && (email == otherPerson.email
                || (email != null && email.equals(otherPerson.email)))
                && (address == otherPerson.address
                || (address != null && address.equals(otherPerson.address)))
                && (telegramHandle == otherPerson.telegramHandle
                || (telegramHandle != null && telegramHandle.equals(otherPerson.telegramHandle)))
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, telegramHandle, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("telegramHandle", telegramHandle)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }

}
