package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.IdentityCodeManager;
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
    private final Remark remark;

    // Data fields
    private final Address address;

    private final IdentityCode identitycode;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a new Person object with all required fields.
     *
     * @param name     The person's name.
     * @param phone    The person's phone number.
     * @param email    The person's email address.
     * @param address  The person's address.
     * @param remark   The person's remark.
     * @param tags     The set of tags associated with the person.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.remark = remark;
        this.identitycode = new IdentityCode(String.valueOf(IdentityCodeManager.getNextIdentityCode()));
    }

    /**
     * Constructs a new Person object with all fields, including the identity code.
     *
     * @param name         The person's name.
     * @param phone        The person's phone number.
     * @param email        The person's email address.
     * @param address      The person's address.
     * @param remark       The person's remark.
     * @param tags         The set of tags associated with the person.
     * @param identityCode The person's identity code.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark,
                  Set<Tag> tags, IdentityCode identityCode) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.remark = remark;
        this.identitycode = identityCode;
    }

    /**
     * Retrieves the name of the person.
     *
     * @return The person's name.
     */
    public Name getName() {
        return name;
    }

    /**
     * Retrieves the phone number of the person.
     *
     * @return The person's phone number.
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Retrieves the email address of the person.
     *
     * @return The person's email address.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Retrieves the address of the person.
     *
     * @return The person's address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Retrieves the remark associated with the person.
     *
     * @return The person's remark.
     */
    public Remark getRemark() {
        return remark;
    }

    /**
     * Retrieves the identity code of the person.
     *
     * @return The person's identity code.
     */
    public IdentityCode getIdentityCode() {
        return this.identitycode;
    }

    /**
     * Returns an immutable set of tags associated with the person.
     * Modifications to the returned set are not allowed.
     *
     * @return An unmodifiable set of tags.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Checks if two persons have the same name, defining a weaker notion of equality.
     *
     * @param otherPerson The other person to compare with.
     * @return True if both persons have the same name, false otherwise.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Checks if two persons have the same identity and data fields, defining a stronger notion of equality.
     *
     * @param other The object to compare with.
     * @return True if both persons are equal, false otherwise.
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
        boolean checkExceptRemarkAttribute = name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags);

        if (remark != null) {
            return checkExceptRemarkAttribute
                    && remark.equals(otherPerson.remark);
        } else {
            return checkExceptRemarkAttribute && otherPerson.remark == null;
        }
    }

    /**
     * Computes the hash code for the Person object.
     *
     * @return The computed hash code.
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, remark, tags);
    }

    /**
     * Returns a string representation of the Person object.
     *
     * @return A string containing the person's information.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("remark", remark)
                .add("tags", tags)
                .add("identitycode", identitycode)
                .toString();
    }
}
