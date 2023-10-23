package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyNumber;
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
    private final Nric nric;
    private final LicencePlate licencePlate;
    private final Policy policy;

    /**
     * Every field must be present and not null.
     * In the case of leads with null policy fields, default values will be put in place by the respective classes.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Nric nric,
                  LicencePlate licencePlate, Policy policy) {
        requireAllNonNull(name, phone, email, nric, licencePlate, address, tags, policy);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.nric = nric;
        this.licencePlate = licencePlate;
        this.policy = policy;
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

    public Nric getNric() {
        return nric;
    }

    public LicencePlate getLicencePlate() {
        return licencePlate;
    }

    public Policy getPolicy() {
        return policy;
    }

    /**
     * Returns true if both persons have the same attributes.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getNric().equals(getNric())
                && otherPerson.getLicencePlate().equals(getLicencePlate())
                && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same policy number
     */
    public boolean comparePolicyNumber(Person otherPerson) {
        if (!this.getPolicy().getPolicyNumber().toString().equals(PolicyNumber.DEFAULT_VALUE)
                && this.getPolicy().getPolicyNumber().equals(otherPerson.getPolicy().getPolicyNumber())) {
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
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && nric.equals(otherPerson.nric)
                && licencePlate.equals(otherPerson.licencePlate)
                && policy.equals(otherPerson.policy);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, nric, licencePlate, policy);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("nric", nric)
                .add("licence plate", licencePlate)
                .add("policy", policy)
                .toString();
    }

}
