package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.AddressBookManager;

/**
 * A utility class to help with building AddressBookManager objects.
 * Example usage: <br>
 *     {@code AddressBookManager ab =
 *              new AddressBookBuilder().withAddressBook(new AddressBookBuilder("CS2103T").withPerson("John")).build();}
 */
public class AddressBookManagerBuilder {

    private AddressBookManager addressBookManager;

    public AddressBookManagerBuilder() {
        addressBookManager = new AddressBookManager();
    }

    public AddressBookManagerBuilder(AddressBookManager addressBookManager) {
        this.addressBookManager = addressBookManager;
    }

    /**
     * Adds a new {@code AddressBook} to the {@code AddressBookManager} that we are building.
     */
    public AddressBookManagerBuilder withAddressBook(AddressBook addressBook) {
        addressBookManager.addAddressBook(addressBook);
        return this;
    }

    /**
     * Sets the active {@code AddressBook} to the {@code AddressBookManager}
     * that we are building with {@code courseCode}.
     */
    public AddressBookManagerBuilder setActiveAddressBook(String courseCode) {
        addressBookManager.setActiveAddressBook(courseCode);
        return this;
    }

    public AddressBookManager build() {
        return addressBookManager;
    }
}
