package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

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
    private final Address address; // Company Address
    private final Company company; // Company Name
    private final TelegramName telegramName;

    /**
     * Constructs a {@code Person}.
     * @param name A valid name.
     * @param phone A valid phone number.
     * @param email A valid email address.
     * @param address An optional address.
     * @param company An optional company.
     * @param telegramName An optional telegram name.
     */
    public Person(Name name, Phone phone, Email email, Address address, Company company, TelegramName telegramName) {
        requireAllNonNull(name, phone, email);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.company = company;
        this.telegramName = telegramName;
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

    public Company getCompany() {
        return company;
    }

    public TelegramName getTelegramName() {
        return telegramName;
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
                && company.equals(otherPerson.company)
                && telegramName.equals(otherPerson.telegramName);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, company, telegramName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("company", company)
                .add("telegramName", telegramName)
                .toString();
    }

    public static String getAllClientNames(HashSet<Person> clients) {
        return clients.stream().map(client -> client.getName().toString()).reduce((a, b) -> a + ", " + b).get();
    }

    /**
     * Creates and returns a dummy Person object with the specified client name.
     * This method is useful for generating placeholder data with a given name.
     *
     * @param clientName The name of the client for whom the dummy Person object is created.
     * @return A dummy Person object with the provided client name and default values for other fields.
     */
    public static Person makeDummyWithName(String clientName) {
        return new Person(new Name(clientName), new Phone("00000"), new Email("filler@email.com"),
                new Address(""), new Company(""), new TelegramName(""));
    }

}
