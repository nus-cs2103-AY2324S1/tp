package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ROLE = "Patient";

    private Name name;
    private Phone phone;
    private Email email;
    private Role role;
    private Set<Affiliation> affiliations;
    private Set<Affiliation> affiliationHistory;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        role = new Role(DEFAULT_ROLE);
        affiliations = new HashSet<>();
        affiliationHistory = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        role = personToCopy.getRole();
        affiliations = new HashSet<>(personToCopy.getAffiliations());
        affiliationHistory = new HashSet<>(personToCopy.getAffiliationHistory());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code affiliations} into a {@code Set<Affiliation>} and
     * set it to the {@code Person} that we are building.
     */
    public PersonBuilder withAffiliations(String... tags) {
        this.affiliations = SampleDataUtil.getAffiliationSet(tags);
        return this;
    }

    /**
     * Parses the {@code affiliationHistory} into a {@code Set<Affiliation>} and
     * set it to the {@code Person} that we are building.
     */
    public PersonBuilder withAffiliationHistory(String... tags) {
        this.affiliationHistory = SampleDataUtil.getAffiliationSet(tags);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Person} that we are building.
     */
    public PersonBuilder withRole(String role) {
        this.role = new Role(role);
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

    public Person build() {
        return new Person(name, phone, email, role, affiliations, affiliationHistory);
    }

}
