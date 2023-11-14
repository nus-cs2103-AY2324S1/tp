package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PreferredContact;
import seedu.address.model.person.PreferredMeetingRegion;
import seedu.address.model.policy.Policy;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_PREFERRED_CONTACT = "";
    public static final String DEFAULT_PREFERRED_MEETING_REGION = "central";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private PreferredContact preferredContact;
    private PreferredMeetingRegion preferredMeetingRegion;
    private Set<Policy> policies;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        preferredContact = new PreferredContact(DEFAULT_PREFERRED_CONTACT);
        preferredMeetingRegion = new PreferredMeetingRegion(DEFAULT_PREFERRED_MEETING_REGION);
        policies = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        preferredContact = personToCopy.getPreferredContact();
        preferredMeetingRegion = personToCopy.getPreferredMeetingRegion();
        policies = new HashSet<>(personToCopy.getPolicies());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code PreferredContact} of the {@code Person} that we are building.
     */
    public PersonBuilder withPreferredContact(String preferredContact) {
        this.preferredContact = new PreferredContact(preferredContact);
        return this;
    }

    /**
     * Sets the {@code PreferredMeetingRegion} of the {@code Person} that we are
     * building.
     */
    public PersonBuilder withPreferredMeetingRegion(String preferredMeetingRegion) {
        this.preferredMeetingRegion = new PreferredMeetingRegion(preferredMeetingRegion);
        return this;
    }

    /**
     * Parses the {@code policyString} into a {@code Policy} and add it to the policies in the
     * {@code Person} that we are building.
     */
    public PersonBuilder withPolicy(String policyString) {
        Policy policy = Policy.fromString(policyString);
        this.policies.add(policy);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, tags, preferredMeetingRegion, preferredContact, policies);
    }

}
