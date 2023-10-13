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
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final Optional<Name> animalName;
    private final Optional<Availability> availability;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Optional<Name> animalName,
                  Optional<Availability> availability, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, animalName, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.animalName = animalName;
        this.availability = availability;
        this.tags.addAll(tags);
    }

    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        this(name, phone, email, address, Optional.empty(), Optional.empty(), tags);
    }

    public Person(Name name, Phone phone, Email email, Address address, Name animalName,
                  Availability availability, Set<Tag> tags) {
        this(name, phone, email, address, Optional.of(animalName), Optional.of(availability), tags);
    }

    public Optional<Name> getAnimalName() {
        return animalName;
    }

    public Optional<Availability> getAvailability() {
        return availability;
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
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && animalName.equals(otherPerson.animalName)
                && availability.equals(otherPerson.availability);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, animalName, availability, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("animalName", animalName)
                .add("availability", availability)
                .toString();
    }

}
