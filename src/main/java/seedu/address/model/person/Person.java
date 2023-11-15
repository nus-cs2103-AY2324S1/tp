package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.policy.Policy;
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
    private final Remark remark;
    /**
     * Every field must be present and not null.
     * In the case of leads with null policy fields, default values will be put in place by the respective classes.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Nric nric,
                  LicencePlate licencePlate, Remark remark, Policy policy) {
        requireAllNonNull(name, phone, email, nric, licencePlate, address, tags, remark, policy);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.nric = nric;
        this.licencePlate = licencePlate;
        this.policy = policy;
        this.remark = remark;
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
     * Compares the policy issue date to the policy expiry date to see which comes first.
     * Returns more than 0 if expiry date is later and less than 0 otherwise
     * @return int
     */
    public int comparePolicyDates() {
        return this.policy.compareDates();
    }

    public Remark getRemark() {
        return remark;
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
     * Returns true if the specified person has the default policy parameters
     */
    public boolean hasDefaultPolicy() {
        return this.policy.isDefaultPolicyProfile();
    }

    /**
     * Returns true if the specified person has any policy parameters equal to the default
     */
    public boolean hasAnyDefaultPolicyParameters() {
        return this.policy.hasDefaultParameter();
    }

    /**
     * Returns true if both persons have the same policy number
     */
    public boolean comparePolicyNumber(Person otherPerson) {
        return this.policy.hasSamePolicyNumber(otherPerson.policy);
    }

    /**
     * Returns true if the person has the default company parameter
     */
    public boolean hasDefaultCompanyPolicyParameter() {
        return this.policy.hasDefaultCompanyParameter();
    }

    /**
     * Returns true if the person has the default policy issue date parameter
     */
    public boolean hasDefaultPolicyIssueDateParameter() {
        return this.policy.hasDefaultIssueDateParameter();
    }

    /**
     * Returns true if the person has the default policy expiry date parameter
     */
    public boolean hasDefaultPolicyExpiryDateParameter() {
        return this.policy.hasDefaultExpiryDateParameter();
    }

    /**
     * Returns true if the person has the default policy number parameter
     */
    public boolean hasDefaultPolicyNumberParameter() {
        return this.policy.hasDefaultNumberParameter();
    }

    /**
     * Returns a person with the same non policy attributes but default policy attributes
     */
    public Person cloneWithoutPolicy() {
        Policy newDefaultPolicy = Policy.createNewDefaultPolicy();
        return new Person(name, phone, email, address, tags, nric, licencePlate, remark, newDefaultPolicy);
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
                && policy.equals(otherPerson.policy)
                && remark.equals(otherPerson.remark);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, nric, licencePlate, remark, policy);
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
                .add("remark", remark)
                .add("policy", policy)
                .toString();
    }

}
