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
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final PreferredContact preferredContact;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final PreferredMeetingRegion preferredMeetingRegion;
    private final Set<Policy> policies = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
            PreferredMeetingRegion preferredMeetingRegion,
            PreferredContact preferredContact, Set<Policy> policies) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.preferredContact = preferredContact;
        this.preferredMeetingRegion = preferredMeetingRegion;
        this.policies.addAll(policies);
    }

    /**
     * Every field must be present and not null.
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     * @param preferredMeetingRegion
     * @param preferredContact
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  PreferredMeetingRegion preferredMeetingRegion,
                  PreferredContact preferredContact) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.preferredContact = preferredContact;
        this.preferredMeetingRegion = preferredMeetingRegion;

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
     * Returns preferred contact method of a person
     */
    public PreferredContact getPreferredContact() {
        return preferredContact;
    }

    /**
     * Returns preferred meeting region of a person
     */
    public PreferredMeetingRegion getPreferredMeetingRegion() {
        return preferredMeetingRegion;
    }

    public Set<Policy> getPolicies() {
        return Collections.unmodifiableSet(policies);
    }

    /**
     * Adds tags to current tags of a person
     */

    public void addTags(Set<Tag> tags) {
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Delete tags from current tags of a person
     */

    public void deleteTags(Set<Tag> originalTags) {
        Set<Tag> newTags = new HashSet<>();
        for (Tag tag : originalTags) {
            if (!this.tags.contains(tag)) {
                newTags.add(tag);
            }
        }
        this.tags.clear();
        this.tags.addAll(newTags);

    }

    /**
     * Adds policy to current policies of a person
     */
    public void addPolicies(Set<Policy> policies) {
        if (policies != null) {
            this.policies.addAll(policies);
        }
    }

    public void removePolicy(Policy policy) {
        this.policies.remove(policy);
    }

    /**
     * Delete tags from current tags of a person
     */
    public void deletePolicies(Set<Policy> originalPolicies) {
        Set<Policy> newPolicies = new HashSet<>();
        for (Policy policy : originalPolicies) {
            if (!this.policies.contains(policy)) {
                newPolicies.add(policy);
            }
        }
        this.policies.clear();
        this.policies.addAll(newPolicies);

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
                && preferredContact.equals(otherPerson.preferredContact)
                && preferredMeetingRegion.equals(otherPerson.preferredMeetingRegion)
                && policies.equals(otherPerson.policies);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, preferredContact, preferredMeetingRegion, policies);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("preferredContact", preferredContact)
                .add("preferredMeetingRegion", preferredMeetingRegion)
                .add("policies", policies)
                .toString();

    }


}
