package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
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
     * Adds a new {@code Patient} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        if (person instanceof Patient) {
            addressBook.addPatient((Patient) person);
        } else if (person instanceof Doctor) {
            addressBook.addDoctor((Doctor) person);
        }
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
