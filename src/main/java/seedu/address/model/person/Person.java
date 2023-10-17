package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final Name animalName;
    private final Availability availability;
    private final Housing housing;
    private final AnimalType animalType;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Housing housing,
                  Availability availability, Name animalName, AnimalType animalType,
                  Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.housing = housing;
        this.availability = availability;
        this.animalName = animalName;
        this.animalType = animalType;
        this.tags.addAll(tags);
    }

    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        this(name, phone, email, address, null, null, null, null, tags);
    }

    /*

    public Person(Name name, Phone phone, Email email, Address address,
                  Housing housing, Availability availability, Set<Tag> tags) {
        this(name, phone, email, address, housing, availability, null, null, tags);
    }

    public Person(Name name, Phone phone, Email email, Address address,
                  Availability availability, Set<Tag> tags) {
        this(name, phone, email, address, null, availability, null, null, tags);
    }

    public Person(Name name, Phone phone, Email email, Address address,
                  Housing housing, Set<Tag> tags) {
        this(name, phone, email, address, housing, null, null, null, tags);
    }

     */

    public Name getAnimalName() {
        return animalName;
    }

    public Availability getAvailability() {
        return availability;
    }

    public Housing getHousing() {
        return housing;
    }

    public AnimalType getAnimalType() {
        return animalType;
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
                && availability.equals(otherPerson.availability)
                && animalType.equals(otherPerson.animalType)
                && housing.equals(otherPerson.housing);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, housing, availability, animalName, animalType, tags);
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
                .add("animalType", animalType)
                .add("housing", housing)
                .toString();
    }

}
