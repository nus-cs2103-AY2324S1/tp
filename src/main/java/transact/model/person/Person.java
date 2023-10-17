package transact.model.person;

import static transact.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import transact.commons.util.ToStringBuilder;
import transact.model.entry.Entry;
import transact.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person implements Entry {
    public static final Person PERSON_UNSTATED = new Person(
            new Name("Unstated"),
            new Phone("Unstated"),
            new Email("Unstated"),
            new Address("Unstated"),
            Collections.emptySet()
    );
    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }
    /**
     * Checks if a given Person object represents an "Unstated" person.
     *
     * @param person The Person object to check.
     * @return True if the Person object represents an "Unstated" person, false otherwise.
     */
    public static Boolean isPersonUnstated(Person person) {
        String name = person.getName().toString();
        String phone = person.getPhone().toString();
        String email = person.getEmail().toString();
        String address = person.getAddress().toString();
        Set<Tag> tag = person.getTags();
        if (name.equalsIgnoreCase("Unstated")
                && email.equalsIgnoreCase("Unstated")
                && phone.equalsIgnoreCase("Unstated")
                && address.equalsIgnoreCase("Unstated")
                && tag.isEmpty()) {
            return true;
        } else {
            return false;
        }
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
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameEntry(Entry otherEntry) {
        if (otherEntry == this) {
            return true;
        }

        if (!(otherEntry instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) otherEntry;
        return otherPerson.getName().equals(getName());
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
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        if (!Person.isPersonUnstated(this)) {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .toString();
        } else {
            return "PersonUnstated";
        }
    }

}
