package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.financialplan.FinancialPlan;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKinName;
import seedu.address.model.person.NextOfKinPhone;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
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
    public static final String DEFAULT_NEXT_OF_KIN_NAME = "Adam Bee";
    public static final String DEFAULT_NEXT_OF_KIN_PHONE = "85555255";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private NextOfKinName nextOfKinName;
    private NextOfKinPhone nextOfKinPhone;
    private Set<FinancialPlan> financialPlans;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        nextOfKinName = new NextOfKinName(DEFAULT_NEXT_OF_KIN_NAME);
        nextOfKinPhone = new NextOfKinPhone(DEFAULT_NEXT_OF_KIN_PHONE);
        financialPlans = new HashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        nextOfKinName = personToCopy.getNextOfKinName();
        nextOfKinPhone = personToCopy.getNextOfKinPhone();
        financialPlans = new HashSet<>(personToCopy.getFinancialPlans());
        tags = new HashSet<>(personToCopy.getTags());
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withFinancialPlans(String ... financialPlans) {
        this.financialPlans = SampleDataUtil.getFinancialPlanSet(financialPlans);
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
     * Sets the {@code NextOfKinName} of the {@code Person} that we are building.
     */
    public PersonBuilder withNextOfKinName(String nokName) {
        this.nextOfKinName = new NextOfKinName(nokName);
        return this;
    }
    /**
     * Sets the {@code NextOfKinPhone} of the {@code Person} that we are building.
     */
    public PersonBuilder withNextOfKinPhone(String nokPhone) {
        this.nextOfKinPhone = new NextOfKinPhone(nokPhone);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, nextOfKinName, nextOfKinPhone, financialPlans, tags);
    }

}
