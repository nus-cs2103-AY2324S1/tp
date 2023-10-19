package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.LicencePlate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDate;
import seedu.address.model.policy.PolicyNumber;
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
    private static final String DEFAULT_NRIC = "567A";
    private static final String DEFAULT_LICENCE_PLATE = "SBC123D";
    private static final String DEFAULT_POLICY_NUMBER = "AIA1234";
    private static final String DEFAULT_POLICY_ISSUE_DATE = "01-01-2023";
    private static final String DEFAULT_POLICY_EXPIRY_DATE = "01-01-2030";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Nric nric;
    private LicencePlate licencePlate;
    private Policy policy;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        nric = new Nric(DEFAULT_NRIC);
        licencePlate = new LicencePlate(DEFAULT_LICENCE_PLATE);
        PolicyNumber policyNumber = new PolicyNumber(DEFAULT_POLICY_NUMBER);
        PolicyDate policyIssueDate = new PolicyDate(DEFAULT_POLICY_ISSUE_DATE);
        PolicyDate policyExpiryDate = new PolicyDate(DEFAULT_POLICY_EXPIRY_DATE);
        policy = new Policy(policyNumber, policyIssueDate, policyExpiryDate);
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
        nric = personToCopy.getNric();
        licencePlate = personToCopy.getLicencePlate();
        policy = personToCopy.getPolicy();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
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
     * Sets the {@code Nric} of the {@code Person} that we are building.
     */
    public PersonBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Sets the {@code LicencePlate} of the {@code Person} that we are building.
     */
    public PersonBuilder withLicencePlate(String licencePlate) {
        this.licencePlate = new LicencePlate(licencePlate);
        return this;
    }

    /**
     * Sets the {@code Policy} of the {@code Person} that we are building.
     */
    public PersonBuilder withPolicy(String policyNumber, String policyIssueDate, String policyExpiryDate) {
        this.policy = new Policy(new PolicyNumber(policyNumber),
                new PolicyDate(policyIssueDate), new PolicyDate(policyExpiryDate));
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, tags, nric, licencePlate, policy);
    }

}
