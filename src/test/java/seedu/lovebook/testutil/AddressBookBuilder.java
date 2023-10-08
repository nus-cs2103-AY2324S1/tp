package seedu.lovebook.testutil;

import seedu.lovebook.model.AddressBook;
import seedu.lovebook.model.person.Date;

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
     * Adds a new {@code Date} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Date date) {
        addressBook.addPerson(date);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
