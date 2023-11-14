package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramName;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_COMPANY = "Google";
    public static final String DEFAULT_TELEGRAM_NAME = "@amybee";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Company company;
    private TelegramName telegramName;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        company = new Company(DEFAULT_COMPANY);
        telegramName = new TelegramName(DEFAULT_TELEGRAM_NAME);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        company = personToCopy.getCompany();
        telegramName = personToCopy.getTelegramName();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code Address} of the {@code Person} that we are building to null.
     */
    public PersonBuilder withAddress() {
        this.address = new Address("");
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
     * Sets the {@code Company} of the {@code Person} that we are building to null.
     */
    public PersonBuilder withCompany() {
        this.company = new Company("");
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code Person} that we are building.
     */
    public PersonBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code TelegramName} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegramName(String telegramName) {
        this.telegramName = new TelegramName(telegramName);
        return this;
    }

    /**
     * Sets the {@code TelegramName} of the {@code Person} that we are building to null.
     */
    public PersonBuilder withTelegramName() {
        this.telegramName = new TelegramName("");
        return this;
    }

    /**
     * Creates dummy person that is created during add event operation as placeholder clients
     */
    public PersonBuilder dummyPersonWithName(String name) {
        this.name = new Name(name);
        this.phone = new Phone("00000");
        this.email = new Email("filler@email.com");
        this.address = new Address("");
        this.company = new Company("");
        this.telegramName = new TelegramName("");
        return this;
    }

    /**
     * Builds a person with the given attributes.
     */
    public Person build() {
        return new Person(name, phone, email, address, company, telegramName);
    }

}
