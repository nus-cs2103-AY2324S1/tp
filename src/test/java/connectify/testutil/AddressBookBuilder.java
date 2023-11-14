package connectify.testutil;

import connectify.model.AddressBook;
import connectify.model.company.Company;
import connectify.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Company} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withCompany(Company company) {
        addressBook.addCompany(company);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
